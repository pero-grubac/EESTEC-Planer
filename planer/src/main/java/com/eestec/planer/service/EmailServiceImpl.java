package com.eestec.planer.service;

import com.eestec.planer.dto.PorukaLoga;
import com.eestec.planer.exception.EmailSendingException;
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
    @Autowired
    private LogService logService;

    @Override
    public void email(String receiver, String username, String subject, String content) {
        System.out.println(receiver + " " + subject + " " + content);
      /*  MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(content);

            javaMailSender.send(message);
        } catch (Exception e) {
            String subjekat = username + ": " + receiver;
            logService.create(PorukaLoga.EMAIL_NIJE_USPJESNO_POSLAT.getValue(), subjekat);
        }*/
    }
}
