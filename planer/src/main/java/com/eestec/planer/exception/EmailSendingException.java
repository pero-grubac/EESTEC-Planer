package com.eestec.planer.exception;

public class EmailSendingException extends RuntimeException {
    public EmailSendingException(String username, String email) {
        super("Can not send email to: " + username + " " + email);
    }
}
