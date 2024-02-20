package com.eestec.planer.service;


public interface EmailService {
    void email(String receiver,String username, String subject, String content);
}
