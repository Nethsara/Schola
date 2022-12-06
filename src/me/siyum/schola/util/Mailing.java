package me.siyum.schola.util;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mailing {
    private static Thread t1;

    public static void startThread(String toEmail, String subject, String message) {
        t1 = new Thread(() -> sendEmail(toEmail, subject, message));
        t1.start();

    }

    public static void stopThread() {
        try {
            t1.stop();
        } catch (NullPointerException e) {
            System.out.println("Sending error");
        }
    }


    public static void sendEmail(String toEmail, String subject, String message) {
        try {
            String fromEmail = "info@kivate.com";
            String username = "3eca03733297d70151b0667829d8a27d";
            String password = "774009ff985f6d88dc9cf4ce59998303";

            Properties props = System.getProperties();
            props.put("mail.smtp.host", "in-v3.mailjet.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.starttls.enable", "true");

            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(true);

            Message mailMessage = new MimeMessage(mailSession);
            mailMessage.setFrom(new InternetAddress(fromEmail));
            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mailMessage.setContent(message, "text/plain");
            mailMessage.setSubject(subject);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect("in-v3.mailjet.com", username, password);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            stopThread();
        }
    }
}