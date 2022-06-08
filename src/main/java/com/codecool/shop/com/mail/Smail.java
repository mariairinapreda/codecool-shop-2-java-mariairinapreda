package com.codecool.shop.com.mail;



import java.util.Properties;
import java.util.logging.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Smail {
    public void send(String recipient) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        final String myEmail = "ioanaserban.iulia";
        final String myPassword = "joxiurojpwkfvbuj";

        Session session_obj = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, myPassword);
            }
        });
        session_obj.setDebug(true);

            Message message = prepareMessage(session_obj, myEmail, recipient);
            Transport.send(message);
            System.out.println("email sent successfully....");

    }

    private Message prepareMessage(Session session_obj, String from_email, String to_email) throws MessagingException {
        try {
            Message msg = new MimeMessage(session_obj);
            msg.setFrom(new InternetAddress(from_email));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
            msg.setSubject("Confirm email");
            msg.setText("Welcome to our shop!");
            return msg;
        }catch (MessagingException mex) {
            Logger.getLogger(Smail.class.getName()).log(Level.SEVERE, null, mex);
        }
        return null;
    }
}