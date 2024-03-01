package com.eestec.planer.service.implementations;

import com.eestec.planer.dto.PorukaLoga;
import com.eestec.planer.exception.EmailSendingException;
import com.eestec.planer.service.EmailService;
import com.eestec.planer.service.LogService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
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

    private boolean validateEmail(String email, String username) {
        try {
            InternetAddress address = new InternetAddress(email);
            address.validate();
            return true;

        } catch (Exception e) {
            logService.create(PorukaLoga.EMAIL_NIJE_USPJESNO_POSLAT.getValue(), username);
        }

        return false;
    }



    @Override
    public void email(String receiver, String username, String subject, String content) {
        if (validateEmail(receiver, username)) {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            try {
                helper.setTo(receiver);
                helper.setSubject(subject);
                helper.setText(content);
                //  javaMailSender.send(message);
            } catch (Exception e) {
                logService.create(PorukaLoga.EMAIL_NIJE_USPJESNO_POSLAT.getValue(), username);
            }
        }

    }
}
