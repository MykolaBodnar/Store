package ua.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailSender {

    private final static String USERNAME = "bodnar.mykola1997@gmail.com";
    private final static String PASSWORD = "mbo999tmnt";
    private final static String EMAILFROM = "bodnar.mykola1997@gmail.com";
    @Async
    public void sendMail(String content, String email, String path) {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAILFROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    EMAILFROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    email));
            message.setSubject(content, "UTF-8");
            message.setText(path);
            synchronized (this) {
                Transport.send(message);
            }
            System.out.println("Message sent without attachment successfully...");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            System.out.println("You have some problems with connection!");
        }
    }
}