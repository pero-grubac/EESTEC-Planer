package com.eestec.planer.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void email(String receiver, String subject, String content) {
        System.out.println(receiver + " " + subject + " " + content);
    /*    MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(content);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // TODO logovati ako email nije dobro poslat
        }*/
    }
}
