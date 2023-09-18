package vn.edu.fpt.horo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.horo.config.kafka.producer.SendEmailProducer;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.cache.UserInfo;
import vn.edu.fpt.horo.dto.event.SendEmailEvent;
import vn.edu.fpt.horo.dto.request.account.*;
import vn.edu.fpt.horo.dto.response.account.*;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorResponse;
import vn.edu.fpt.horo.dto.response.follow.GetCountFollowPost;
import vn.edu.fpt.horo.entity.*;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.mapper.AccountMapper;
import vn.edu.fpt.horo.mapper.AdvisorMapper;
import vn.edu.fpt.horo.repository.*;
import vn.edu.fpt.horo.service.AccountService;
import vn.edu.fpt.horo.service._TokenService;
import vn.edu.fpt.horo.utils.AuditorUtils;
import vn.edu.fpt.horo.utils.AuthenticationUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * vn.edu.fpt.accounts.service.impl
 **/

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final static Integer RECOMMEND_PASSWORD_LENGTH = 8;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final _TokenService tokenService;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    private final SendEmailProducer sendEmailProducer;
    private final AccountMapper accountMapper;
    private final AdvisorMapper advisorMapper;

    private final CoinRepository coinRepository;
    private final PostRepository postRepository;
    private final AdvisorRepository advisorRepository;

    @Override
    public void init() {
        if (accountRepository.findAccountByEmailOrUsername("admin").isEmpty()) {
            _Role adminRole = roleRepository.findByRoleName("ADMIN")
                    .orElseThrow(() -> new BusinessException("Role ADMIN not exist"));
            Account account = Account.builder()
                    .email("admin.horo@gmail.com")
                    .username("admin")
                    .fullName("admin")
                    .password(passwordEncoder.encode("123123aA@"))
                    .roles(java.util.List.of(adminRole))
                    .build();
            try {
                accountRepository.save(account);
//                pushAccountInfo(account);
                log.info("Init account success");
            } catch (Exception ex) {
                throw new BusinessException("Can't init account in database: " + ex.getMessage());
            }
        }
    }

    @Override
    public UserDetails getUserByUsername(String username) {
        Account account = accountRepository.findAccountByEmailOrUsername(username)
                .orElseThrow(() -> new BusinessException("Username or email not found!"));
        java.util.List<_Role> roles = account.getRoles();
        return User.builder()
                .username(account.getAccountId())
                .password(account.getPassword())
                .authorities(this.getAuthorities(roles).toArray(String[]::new))
                .build();
    }

    @Override
    public void changeEmail(String id, ChangeEmailRequest request) {
        Account account = accountRepository.findAccountByAccountId(id)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account ID not exist!"));

        if (accountRepository.findAccountByEmail(request.getNewEmail()).isPresent()) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Email already exist!");
        }
        account.setEmail(request.getNewEmail());

        pushAccountInfo(account);

        account.setLastModifiedDate(LocalDateTime.now());
        try {
            accountRepository.save(account);
            log.info("Change email success");
        } catch (Exception ex) {
            log.error("Change email failed: {}", ex.getMessage());
            throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, "Can't change email to database: " + ex.getMessage());
        }
    }

    @Override
    public void changePassword(String id, ChangePasswordRequest request) {
        Account account = accountRepository.findAccountByAccountId(id)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account ID not exist!"));
        if (Boolean.FALSE.equals(passwordEncoder.matches(request.getOldPassword(), account.getPassword()))) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Password incorrect!");
        }
        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
        account.setLastModifiedDate(LocalDateTime.now());
        try {
            accountRepository.save(account);
            log.info("Change password success");
        } catch (Exception ex) {
            throw new BusinessException("Can't not save account to database when change password: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public CreateAccountResponse createAccount(CreateAccountRequest request) {

        _Role defaultRole = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new BusinessException("Role USER not found in database"));
        if (Objects.nonNull(request.getPassword())) {
            request.setPassword(request.getPassword());
        }
        if (accountRepository.findAccountByUsername(request.getUsername()).isPresent()) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Username already exist");
        }
        if (accountRepository.findAccountByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Email already exist");
        }
        Coin coin = Coin.builder()
                .amount(BigDecimal.ZERO)
                .build();
        coinRepository.save(coin);
        Account account = Account.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(java.util.List.of(defaultRole))
                .coin(coin)
                .isOnline(false)
                .verified(false)
                .createdDate(LocalDateTime.now())
                .build();

        try {
            accountRepository.save(account);
            log.info("Create account success with email: {} and account id is: {}", account.getEmail(), account.getAccountId());
        } catch (Exception ex) {
            throw new BusinessException("Can't create account in database: " + ex.getMessage());
        }

//        pushAccountInfo(account);

        return CreateAccountResponse.builder()
                .accountId(account.getAccountId())
                .build();
    }



    @Override
    public LoginResponse login(LoginRequest request) {
        Account account = accountRepository.findAccountByEmailOrUsername(request.getEmailOrUsername())
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Username or email not exist!"));
        if (Boolean.FALSE.equals(passwordEncoder.matches(request.getPassword(), account.getPassword()))) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Password incorrect!");
        }
        if (Boolean.FALSE.equals(account.getIsCredentialNonExpired())) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account credential expired!");
        }
        if (Boolean.FALSE.equals(account.getIsNonExpired())) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account expired!");
        }
        if (Boolean.FALSE.equals(account.getIsNonLocked())) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account locked");
        }
        if (Boolean.FALSE.equals(account.getIsEnabled())) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account disable!");
        }
        if (request.getIsAdmin() != null && Boolean.TRUE.equals(request.getIsAdmin()) && account.getRoles().stream()
                .noneMatch(role -> "ADMIN".equalsIgnoreCase(role.getRoleName()))) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account Not Admin Account!");
        }

        String token = tokenService.generateToken(account, getUserByUsername(account.getUsername()));
        String refreshToken = tokenService.generateRefreshToken(request);
        account.setIsOnline(true);
        accountRepository.save(account);
        return LoginResponse.builder()
                .accountId(account.getAccountId())
                .username(account.getUsername())
                .fullName(account.getFullName())
                .email(account.getEmail())
                .token(token)
                .tokenExpireTime(tokenService.getExpiredTimeFromToken(token))
                .refreshToken(refreshToken)
                .refreshTokenExpireTime(tokenService.getExpiredTimeFromToken(refreshToken))
                .build();
    }

    @Override
    public LoginResponse loginWithGoogle(String email) {
        Account account = accountRepository.findAccountByEmail(email)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Username or email not exist!"));
        String token = tokenService.generateToken(account, getUserByUsername(account.getUsername()));
        String refreshToken = tokenService.generateRefreshToken(LoginRequest.builder().emailOrUsername(email).password(account.getPassword()).build());
        account.setIsOnline(true);
        accountRepository.save(account);
        return LoginResponse.builder()
                .accountId(account.getAccountId())
                .username(account.getUsername())
                .fullName(account.getFullName())
                .email(account.getEmail())
                .token(token)
                .tokenExpireTime(tokenService.getExpiredTimeFromToken(token))
                .refreshToken(refreshToken)
                .refreshTokenExpireTime(tokenService.getExpiredTimeFromToken(refreshToken))
                .build();
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest request) {
        LoginRequest loginRequest = tokenService.getLoginRequestFromToken(request.getRefreshToken());
        return login(loginRequest);
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {
        Account account = accountRepository.findAccountByEmailOrUsername(request.getEmailOrUsername())
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account not found"));
        String newPassword = randomPassword();
        account.setPassword(passwordEncoder.encode(newPassword));

        SendEmailEvent sendEmailEvent = SendEmailEvent.builder()
                .sendTo(account.getEmail())
                .bcc(null)
                .cc(null)
                .templateId("6369cc43f258642ab1e18504")
                .params(Map.of("NEW_PASSWORD", newPassword))
                .build();

        sendEmailProducer.sendMessage(sendEmailEvent);

        try {
            accountRepository.save(account);
            log.info("Reset password success");
        } catch (Exception ex) {
            throw new BusinessException("Can't reset password account to database: " + ex.getMessage());
        }
    }

    @Override
    public void addRoleToAccount(String id, AddRoleToAccountRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account id not found"));

        _Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Role id not found"));

        java.util.List<_Role> roles = account.getRoles();
        Optional<_Role> roleInAccount = roles.stream().filter(v -> v.getRoleId().equals(request.getRoleId())).findAny();
        if (roleInAccount.isPresent()) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Role already in account");
        }
        roles.add(role);
        account.setRoles(roles);

        try {
            accountRepository.save(account);
            log.info("Add role to account success");
            pushAccountInfo(account);
        } catch (Exception ex) {
            throw new BusinessException("Can't save account update role to database");
        }
    }

    @Override
    public void deleteAccountById(String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account not found"));
        try {
            accountRepository.delete(account);
            log.info("Delete account success");
        } catch (Exception ex) {
            throw new BusinessException("Delete account failed: " + ex.getMessage());
        }

        removeAccountInfo(account);
    }

    @Override
    public void removeRoleFromAccount(String id, String roleId) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account not found"));
        Optional<_Role> optionalRole = account.getRoles().stream().filter(v -> v.getRoleId().equals(roleId)).findAny();
        if (optionalRole.isEmpty()) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Role id not exist in account");
        }
        _Role role = optionalRole.get();
        java.util.List<_Role> roles = account.getRoles();
        roles.remove(role);

        account.setRoles(roles);

        pushAccountInfo(account);
        try {
            accountRepository.save(account);
            log.info("Remove role from account success");
        } catch (Exception ex) {
            throw new BusinessException("Can't save account after remove role to database: " + ex.getMessage());
        }
    }

    @Override
    public Boolean changeOnlineStatus(String accountId, Boolean isOn) {
        Account account = accountRepository.findAccountByAccountId(accountId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account not found"));
        if (!account.getIsOnline().equals(isOn)) {
            account.setIsOnline(isOn);
            accountRepository.save(account);
        }
        return account.getIsOnline();
    }


    @Override
    public AccountResponse getAccountDetail(String accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow();
        return accountMapper.mapGetAccountDetailResponse(account);
    }

    @Override
    public void folowingAdvisor(String advisorId) {
        String accountId = AuditorUtils.getUserIdInToken();
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account id not found"));
        Advisor advisor = advisorRepository.findById(advisorId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Advisor is not advisor"));
        List<Account> accountList = advisor.getFollowers();
        if(accountList.contains(account)){
            accountList.remove(account);
        }else{
            accountList.add(account);
        }
        advisor.setFollowers(accountList);
        List<Advisor> followings = account.getFollowing();
        if(followings.contains(advisor)){
            followings.remove(advisor);
        }else {
            followings.add(advisor);
        }
        account.setFollowing(followings);
        accountRepository.save(account);
        advisorRepository.save(advisor);

    }

    @Override
    public Page<GetAdvisorResponse> getAccountFollowings(String accountId, Pageable pageable) {
        Account account = accountRepository.findAccountByAccountId(accountId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account id not found"));
        int indexFrom = pageable.getPageSize() * pageable.getPageNumber();
        int indexTo = indexFrom + pageable.getPageSize();
        int size = account.getFollowing().size();
        Page<Advisor> followings;
        if(size < indexFrom){
            followings = new PageImpl<>(Collections.emptyList(), pageable, size);
        }else if(size < indexTo){
            followings = new PageImpl<>(account.getFollowing().subList(indexFrom, size), pageable, size);
        }else {
            followings = new PageImpl<>(account.getFollowing().subList(indexFrom, indexTo), pageable, size);
        }
        return followings.map(advisorMapper::mapToGetAdvisorDetailResponse);

    }


    @Override
    public GetCountFollowPost getCountFollowPost(String accountId) {
        Account account = accountRepository.findAccountByAccountId(accountId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account id not found"));
        Optional<Advisor> advisor = advisorRepository.findFirstByAccount_AccountId(accountId);

        return GetCountFollowPost.builder()
                .countFollower(advisor.isPresent() ? advisor.get().getFollowers().size() : 0)
                .countFollowTo(account.getFollowing().size())
                .countPost(postRepository.countAllByCreatedBy_AccountId(accountId))
                .build();
    }

    private String randomPassword() {

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int[] randomNumber;
        do {
            randomNumber = random.ints(RECOMMEND_PASSWORD_LENGTH * 5L, 48, 122)
                    .filter(x -> x > 97 && x < 122 || x > 65 && x < 90 || x > 48 && x < 57)
                    .toArray();
        } while (randomNumber.length < RECOMMEND_PASSWORD_LENGTH);

        for (int i = 0; i < RECOMMEND_PASSWORD_LENGTH; i++) {
            stringBuilder.append((char) randomNumber[i]);
        }
        return stringBuilder.toString();
    }

    private java.util.List<String> getAuthorities(java.util.List<_Role> roles) {
        java.util.List<String> authorities = new ArrayList<>();
        java.util.List<_Role> roleEnable = roles.stream()
                .filter(_Role::getIsEnable).collect(Collectors.toList());
        authorities.addAll(roleEnable.stream()
                .map(_Role::getRoleName)
                .map(AuthenticationUtils::addRolePrefix)
                .collect(Collectors.toList()));

        return authorities;
    }


    private void pushAccountInfo(Account account) {
        UserInfo userInfo = UserInfo.builder()
                .email(account.getEmail())
                .username(account.getUsername())
                .fullName(account.getFullName())
                .roles(account.getRoles().stream().map(_Role::getRoleName).collect(Collectors.toList()))
                .build();

        try {
            redisTemplate.opsForValue().set(String.format("userinfo:%s", account.getAccountId()), objectMapper.writeValueAsString(userInfo));
            log.info("Push UserInfo to Redis success");
        } catch (JsonProcessingException ex) {
            throw new BusinessException("Can't push user info to Redis: " + ex.getMessage());
        }
    }

    private void removeAccountInfo(Account account) {
        try {
            Boolean result = redisTemplate.delete(String.format("userinfo:%s", account.getAccountId()));
            if (Boolean.TRUE.equals(result)) {
                log.info("remove account info success");
            } else {
                log.info("account info has been deleted");
            }
        } catch (Exception ex) {
            throw new BusinessException("Can't remove userinfo in redis: " + ex.getMessage());
        }
    }
}

