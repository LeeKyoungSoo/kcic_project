package com.bethesda.kcic.util;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailSend {
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "ilovebasic@gmail.com";

    public void SendEmail(String mailAddress, String mailTitle, String mailCont)  {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailAddress);
        message.setFrom(FROM_ADDRESS);
        message.setSubject(mailTitle);
        message.setText(mailCont);

        mailSender.send(message);
    }
}
