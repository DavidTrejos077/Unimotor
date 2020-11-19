package co.edu.uniquindio.unimotor.beans;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
  
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
  
/**
 * A utility class for sending e-mail messages
 * @author www.codejava.net
 *
 */
public class EmailUtility {
    public static void sendEmail(String host, String port,final String senderEmail, String senderName, final String password,
            String recipientEmail, String subject, String message) throws AddressException,
            MessagingException, UnsupportedEncodingException {
    	try {
    		System.out.println("Enviando Correo");
    		System.out.println("host="+host);
    		System.out.println("port="+port);
    		System.out.println("senderEmail="+senderEmail);
    		System.out.println("senderName="+senderName);
    		System.out.println("password="+password);
    		System.out.println("recipientEmail= "+recipientEmail);
    		System.out.println("subject="+subject);
    		System.out.println("message="+message);
    		
//        	String host = "";
//        	String port = "";
//        	String senderEmail = "";
//        	String senderName = "";
//        	String password = "";
//        	String host = "";
            // sets SMTP server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
      
            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, password);
                }
            };
      
            Session session = Session.getInstance(properties, auth);
      
            // creates a new e-mail message
            Message msg = new MimeMessage(session);
      
            msg.setFrom(new InternetAddress(senderEmail, senderName));
            InternetAddress[] toAddresses = { new InternetAddress(recipientEmail) };
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(message);
      
            // sends the e-mail
            Transport.send(msg);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
  
    }
}