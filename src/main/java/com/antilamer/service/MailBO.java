package com.antilamer.service;

import javax.mail.Address;
import javax.mail.MessagingException;

public interface MailBO {
    void sendMail(Address[] addresses, String subject, String text) throws MessagingException;
}
