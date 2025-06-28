package org.example.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class EmailUtil {
    @Async
    public static void sendAttachmentEmail(Session session, String toEmail, String subject, String htmlContent) throws UnsupportedEncodingException {
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("it.1703302@gmail.com", "Election team"));

            msg.setReplyTo(InternetAddress.parse("it.1703302@gmail.com ", false));

            msg.setSubject(subject, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            // Create the HTML message body part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(htmlContent, "text/html; charset=UTF-8");

            // Create a multipart message for attachment
            Multipart multipart = new MimeMultipart();

            // Set HTML message part
            multipart.addBodyPart(messageBodyPart);



            // Set content of the message
            msg.setContent(multipart);

            // Send message
            Transport.send(msg);
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println("Exception "+e);
        }
    }
}
