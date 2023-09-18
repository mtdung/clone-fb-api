package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.entity.Account;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.repository.AccountRepository;
import vn.edu.fpt.horo.service.EmailService;
import vn.edu.fpt.horo.utils.AuditorUtils;
import vn.edu.fpt.horo.utils.RequestDataUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Value("${host-port.ec2}")
    private String domain;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void sendVerifyEmail(String email) {
        sendMail("Verify your email address", "Please click the link below to verify your email address:\n"
                + "http://"+domain+"/horo/public/api/v1/email/verify?email=" + email, email);
    }

    void sendMail(String subject, String content, String toEmail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailFrom);
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailSender.send(mailMessage);
    }

    @Override
    public String verifyEmail(String email) {
        Account account = accountRepository.findAccountByEmail(email)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account email not exist"));
        account.setVerified(true);
        accountRepository.save(account);
        return "Verify successfully! Please come back to Horo Application!";
    }

    String getAccountEmail() {
        Account account = accountRepository.findAccountByAccountId(AuditorUtils.getUserIdInToken())
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account id not exist"));
        return account.getEmail();
    }

    @Override
    public String resetPassword(String username) {
        Account account = accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account id not exist"));
        String newPassword = RequestDataUtils.generatePassword(8);
        sendMail("Your password has been changed!",
                "Your new password is: " + newPassword + " please change your password after login !",
                account.getEmail());
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
        return "Reset Success";
    }
}
