package com.ua.fishingforum.security.service.impl;

import com.ua.fishingforum.security.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationEmailService implements EmailService {
    private final MailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void send(String email) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setFrom(from);
        simpleMessage.setTo(email);
        simpleMessage.setText("please click this link + <a href=\"localhost:8080/api/v1/verification\">клікніть тут</a>");
        mailSender.send(simpleMessage);

    }
}
