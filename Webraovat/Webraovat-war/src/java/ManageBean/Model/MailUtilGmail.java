/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean.Model;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import static javax.ws.rs.client.Entity.html;

/**
 *
 * @author Asus
 */
public class MailUtilGmail {

    public static void sendMail(String to, String from, String subject, String body, boolean bodyIsHTML)
            throws MessagingException {
        //1 get mail

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", 465);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        //2 cretea
        Message message = new MimeMessage(session);
        //
        Multipart multiPart = new MimeMultipart("alternative");
       
        //set part of html
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(body, "text/html; charset=utf-8");
        multiPart.addBodyPart(htmlPart);
        //set subject of mail
        message.setSubject(subject);
        //set body of mail
        if (bodyIsHTML) {

            message.setContent(multiPart);
        } else {
            message.setText(body);

        }

        //3
        Address fromAddress = new InternetAddress(from);
        Address toAddres = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddres);

        //4
        Transport transport = session.getTransport();
        transport.connect("lisatthu35@gmail.com", "lisatthu35");
        transport.sendMessage(message, message.getAllRecipients());

        transport.close();
    }

}
