package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.AdvisorStatus;
import vn.edu.fpt.horo.constant.BookingStatus;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;

import vn.edu.fpt.horo.constant.SlotTimeEnum;
import vn.edu.fpt.horo.dto.request.advisor.UpdateAdvisorRequest;
import vn.edu.fpt.horo.dto.response.account.AccountResponse;
import vn.edu.fpt.horo.dto.response.advisor.AdvisorFreeTimeResponse;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorByTopicResponse;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorResponse;
import vn.edu.fpt.horo.dto.response.service_package.GetServicePackageResponse;
import vn.edu.fpt.horo.entity.*;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.mapper.AccountMapper;
import vn.edu.fpt.horo.mapper.AdvisorMapper;
import vn.edu.fpt.horo.mapper.PackageServiceMapper;
import vn.edu.fpt.horo.repository.*;
import vn.edu.fpt.horo.service.AdvisorService;
import vn.edu.fpt.horo.service.FileService;
import vn.edu.fpt.horo.utils.AuditorUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class AdvisorServiceImpl implements AdvisorService {
    private final BookingRepository bookingRepository;

    private final AdvisorRepository advisorRepository;
    private final AdvisorMapper advisorMapper;
    private final TopicRepository topicRepository;
    private final PackageServiceRepository packageServiceRepository;
    private final PackageServiceMapper packageServiceMapper;
    private final AccountMapper accountMapper;
    private final FileService fileService;
    private final AccountRepository accountRepository;

    @Override
    public GetAdvisorResponse getAdvisorDetail(String accountId) {
        Advisor advisor = advisorRepository.findFirstByAccount_AccountId(accountId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Advisor not found"));
        GetAdvisorResponse response = advisorMapper.mapToGetAdvisorDetailResponse(advisor);
        List<GetAdvisorResponse.TopicResponse> topics = new ArrayList<>();
        for (GetAdvisorResponse._PackageServiceResponse packageService: response.getPackageServices()
             ) {
            topics.add(packageService.getTopic());
        }
        response.setTopics(topics);
        return response;
    }

    @Override
    public Page<GetAdvisorResponse> getAdvisor(String name, Integer page, Integer size) {
        Page<Advisor> advisors = null;
        if (name == null || name.equals("")) {
            advisors = advisorRepository.findByAccount_AccountIdNot(AuditorUtils.getUserIdInToken(), PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdDate"))));
        } else {
            advisors = advisorRepository.findByAccount_AccountIdNotAndAccount_FullNameContaining(AuditorUtils.getUserIdInToken(), name, PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdDate"))));
        }
        return advisors.map(advisorMapper::mapAdvisorResponse);
    }

    @Override
    public List<GetServicePackageResponse> getAdvisorServicePackage(String advisorId) {
        Advisor advisor = advisorRepository.findById(advisorId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Advisor ID not found"));
        return advisor.getPackageServices().stream().filter(sv->sv.getTopic().isActive()).map(packageServiceMapper::mapServicePackageResponse).collect(Collectors.toList());
    }

    @Override
    public void updateAdvisor(String advisorId, UpdateAdvisorRequest request) {
        Advisor advisor = advisorRepository.findById(advisorId)
                .orElseThrow();
        advisorMapper.updateAdvisor(advisor, request);
        if(request.getAvatar() != null) {
            _File avatarFile = fileService.getFileById(request.getAvatar());
            advisor.getAccount().getProfile().setAvatar(avatarFile);
        }
        advisorRepository.save(advisor);
    }

    // Co ng book tra true ranh trả false
    @Override
    public AdvisorFreeTimeResponse getFreeTime(String advisorId, LocalDate date) {
        try {
            advisorRepository.findById(advisorId)
                    .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Advisor not exist"));
            List<Booking> bookings =
                    bookingRepository.findAllByAdvisor_AdvisorIdAndStatusInAndBookingTimeBetween(advisorId,
                            List.of(BookingStatus.APPROVED, BookingStatus.PROCESSING, BookingStatus.WAITING_FOR_APPROVAL),
                            date.atTime(LocalTime.MIN), date.atTime(LocalTime.MAX));
            Map<Integer, Boolean> freeTime = new HashMap<>();
            bookings.forEach(v -> {
                freeTime.put(v.getSlot(), Boolean.FALSE);
            });
            LocalTime now = LocalTime.now();
            int hourNow = now.getHour();
            LocalDate today = LocalDate.now();
            int slotBefore = 0;
            if (date.equals(today)) {
                slotBefore = SlotTimeEnum.getSlotsBefore(hourNow);
            } else if (date.isBefore(today)) {
                slotBefore = SlotTimeEnum.countSlot();
            }
            for (int i = 1; i <= SlotTimeEnum.countSlot(); i++) {
                if (!freeTime.containsKey(i)) {
                    if (i <= slotBefore) {
                        freeTime.put(i, Boolean.FALSE);
                    } else {
                        freeTime.put(i, Boolean.TRUE);
                    }
                }
            }

            return AdvisorFreeTimeResponse.builder()
                    .slots(freeTime)
                    .build();
        } catch (Exception e) {
            throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public List<GetAdvisorByTopicResponse> getListAdvisorByTopic(String topicId) {
        topicRepository.findById(topicId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Topic id not exist"));
        List<_PackageService> packageService = packageServiceRepository.findByTopicTopicId(topicId);
        List<Advisor> advisors = packageService.stream().map(_PackageService::getAdvisor).collect(Collectors.toList());
        return advisors.stream().map(advisorMapper::mapGetListAdvisorByTopic).collect(Collectors.toList());
    }

    @Override
    public Boolean checkIsAdvisor(String accountId) {
        Optional<Advisor> advisor = advisorRepository.findFirstByAccount_AccountIdAndStatus(accountId, AdvisorStatus.ACTIVE);
        return advisor.isPresent();
    }

    @Override
    public List<GetAdvisorByTopicResponse> searchAdvisorByName(String advisorName) {
        List<GetAdvisorByTopicResponse> getAdvisorByTopicResponses = new ArrayList<>();
        List<Account> accounts = accountRepository.findAccountByName(advisorName);
        for (Account account : accounts) {
            Advisor advisor = advisorRepository.findFirstByAccount_AccountId(account.getAccountId()).orElse(null);
            if (advisor != null) {
                getAdvisorByTopicResponses.add(advisorMapper.mapGetListAdvisorByTopic(advisor));
            }
        }
        return getAdvisorByTopicResponses;
    }

    @Override
    public Page<AccountResponse> getAccountFollower(String advisorId, Pageable pageable) {
        Advisor advisor = advisorRepository.findById(advisorId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Advisor not exist"));
        int indexFrom = pageable.getPageSize() * pageable.getPageNumber();
        int indexTo = indexFrom + pageable.getPageSize();
        int size = advisor.getFollowers().size();
        Page<Account> followers;
        if(size < indexFrom){
            followers = new PageImpl<>(Collections.emptyList(), pageable, size);
        }else if(size < indexTo){
            followers = new PageImpl<>(advisor.getFollowers().subList(indexFrom, size), pageable, size);
        }else {
            followers = new PageImpl<>(advisor.getFollowers().subList(indexFrom, indexTo), pageable, size);
        }
        return followers.map(accountMapper::mapGetAccountDetailResponse);

    }

    @Override
    public String deleteAdvisor(String advisorId) {
        Advisor advisor = advisorRepository.findById(advisorId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Advisor not exist"));
        advisorRepository.delete(advisor);
        return "Delete Advisor Successfully";
    }

}
