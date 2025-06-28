package org.example.service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
@Service
public class EmailService {
    @Value("${gmail}")
    private String from;

    @Value("${password.email}")
    private String password;

    @Value("${username.email}")
    private String username;

    @Async
    public void sendEmail(String email, String userName) throws UnsupportedEncodingException {
     System.out.println(" Send email "+email +" Name "+userName);
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
        props.put("mail.smtp.port", "587"); // TLS Port
        props.put("mail.smtp.auth", "true"); // enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

        // create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            // override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        // replace with actual user name
System.out.println("Name "+userName + " Email "+email);
        EmailUtil.sendAttachmentEmail(session, email, "Welcome, Voter! Your Registration is Confirmed", getBody());

    }

    private String getBody() {
        return "<div style=\"max-width:550px; padding:18px; border:1px solid #dadada; border-radius:10px; font-family:Arial, Helvetica, sans-serif; font-size:15px; color:#495057;\">"
                + "<p style=\"font-size:17px; font-weight:bold;\">Dear Citizen,</p>"
                + "<p>Congratulations! You have been successfully registered as a voter on our secure E-Voting platform.</p>"
                + "<p>Your voice matters. Your vote is a powerful contribution to shaping the future of our nation.</p>"
                + "<p>Stay tuned for upcoming election notifications. Make sure to cast your vote and participate in building a stronger democracy.</p>"
                + "<p>Thank you for being a responsible citizen.</p>"
                + "<p>Warm regards,<br><strong>E-Voting System Team</strong></p>"
                + "</div>";
    }


    @Async
    public void sendEmailForVoteDoneVoter(String email, String userName,String partyName,String candidateName) throws UnsupportedEncodingException {
        System.out.println(" Send email "+email +" Name "+userName);
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
        props.put("mail.smtp.port", "587"); // TLS Port
        props.put("mail.smtp.auth", "true"); // enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

        // create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            // override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        // replace with actual user name
        System.out.println("Name "+userName + " Email "+email);
        EmailUtil.sendAttachmentEmail(session, email, "Welcome, Voter! Your Registration is Confirmed", getBodyForVoter(candidateName,partyName));

    }

    private String getBodyForVoter(String candidateName, String partyName) {
        return "<div style=\"max-width:550px; padding:18px; border:1px solid #dadada; border-radius:10px; font-family:Arial, Helvetica, sans-serif; font-size:15px; color:#495057;\">"
                + "<p style=\"font-size:17px; font-weight:bold;\">Dear Citizen,</p>"
                + "<p>Thank you for casting your vote in this election. Your vote has been successfully recorded.</p>"
                + "<p>You voted for <strong>" + candidateName + "</strong> of the <strong>" + partyName + "</strong> party.</p>"
                + "<p>Your participation strengthens our democracy and helps shape the future of our nation.</p>"
                + "<p>We appreciate your commitment to civic responsibility.</p>"
                + "<p>Warm regards,<br><strong>E-Voting System Team</strong></p>"
                + "</div>";
    }


    public void sendEmailToCandidate(String email, String userName, String party) throws UnsupportedEncodingException {



        System.out.println(" Send email "+email +" Name "+userName);
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
        props.put("mail.smtp.port", "587"); // TLS Port
        props.put("mail.smtp.auth", "true"); // enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

        // create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            // override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        // replace with actual user name
        System.out.println("Name "+userName + " Email "+email);
        EmailUtil.sendAttachmentEmail(session, email, "Candidate Registration Successful – Welcome to the E-Voting Platform", getBodyForCandidate(userName,party));
    }

    private String getBodyForCandidate(String candidateName, String partyName) {
        return "<div style=\"max-width:550px; padding:18px; border:1px solid #dadada; border-radius:10px; font-family:Arial, Helvetica, sans-serif; font-size:15px; color:#495057;\">"
                + "<p style=\"font-size:17px; font-weight:bold;\">Dear " + candidateName + ",</p>"
                + "<p>Congratulations! You have been successfully registered as a candidate on our secure E-Voting platform.</p>"
                + "<p>You will be contesting under the <strong>" + partyName + "</strong> party.</p>"
                + "<p>We appreciate your commitment to public service and wish you all the best in your campaign.</p>"
                + "<p>Our platform ensures fair and transparent elections, and your participation adds great value to this mission.</p>"
                + "<p>Warm regards,<br><strong>E-Voting System Team</strong></p>"
                + "</div>";
    }

}
