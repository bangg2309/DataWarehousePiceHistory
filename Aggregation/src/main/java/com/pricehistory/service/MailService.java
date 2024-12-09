package com.pricehistory.service;

import com.pricehistory.util.PropUtil;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {

    private final String mailHost = PropUtil.getProp("mail.smtp.host");
    private final String mailPort = PropUtil.getProp("mail.smtp.port");
    private final String mailAuth = PropUtil.getProp("mail.smtp.auth");
    private final String mailStarttls = PropUtil.getProp("mail.smtp.starttls.enable");
    private final String mailUsername = PropUtil.getProp("mail.username");
    private final String mailPassword = PropUtil.getProp("mail.password");
    private final String to = PropUtil.getProp("mail.to");


    private static MailService instance;

    private MailService() {

    }

    public static MailService getInstance() {
        if (instance == null) {
            instance = new MailService();
        }
        return instance;
    }

    public void sendMail(String subject, String content) {
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUsername, mailPassword);
            }
        };
        Properties prop = new Properties();
        prop.setProperty("mail.smtp.host", mailHost);
        prop.setProperty("mail.smtp.port", mailPort);
        prop.setProperty("mail.smtp.auth", mailAuth);
        prop.setProperty("mail.smtp.starttls.enable", mailStarttls);
        Session session = Session.getInstance(prop, auth);

        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(mailUsername));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject, "UTF-8");
            msg.setText(content, "UTF-8");
            javax.mail.Transport.send(msg);
            System.out.println("Sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}