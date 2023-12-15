package com.example.securitywithdynamicrole.service.account;

import com.bastiaanjansen.otp.TOTPGenerator;
import com.example.securitywithdynamicrole.entity.account.Account;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetPasswordService {

    private final JavaMailSender javaMailSender;
    private final TOTPGenerator generator;

    public void sendMail(Account account) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("poe@poe.com");
        mailMessage.setTo(account.getEmail());
        mailMessage.setSubject("Reset Password");
        mailMessage.setText("Your opt code is %n %s".formatted(generator.now()));
        javaMailSender.send(mailMessage);
    }
}
