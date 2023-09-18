package vn.edu.fpt.horo.service;

public interface EmailService {
    void sendVerifyEmail(String email);

    String verifyEmail(String email);

    String resetPassword(String username);
}
