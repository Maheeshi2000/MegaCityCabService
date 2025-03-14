package com.vehicle.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public static void sendEmail(String to, String subject, String messageContent) {
        final String senderEmail = "dhupikaneo16@gmail.com"; // Your Gmail
        final String senderPassword = "fcibfpinwdgsyjbj"; // Use your App Password

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(messageContent);

            Transport.send(message);
            System.out.println("✅ Email sent successfully to: " + to);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
