package com.antilamer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service(value = "mailBO")
public class MailBOImpl implements MailBO {

    private Session session;

    @Value("${mail.server.email}")
    private String serverEmail;

    @Value("${mail.server.password}")
    private String password;

    @Override
    public void sendMail(Address[] addresses, String subject, String text) throws MessagingException {
        Session session = getSession();
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(serverEmail));
        message.setRecipients(Message.RecipientType.TO, addresses);
        message.setSubject(subject);
        message.setText(text);

        Transport.send(message);
    }

    private Session getSession() {
        if (session != null){
            return session;
        } else {
            initSession();
            return session;
        }
    }

    private void initSession(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(serverEmail, password);
                    }
                });
    }
}
