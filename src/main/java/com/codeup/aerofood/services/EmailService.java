package com.codeup.aerofood.services;

//import org.apache.catalina.User;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("mailService")
public class EmailService {
    @Value("${app.sendgrid.templateId}")
    private String templateId;

    @Autowired
    SendGrid sendGrid;
    public String sendEmail(String email) {

        try {
        Mail mail = prepareMail(email);

        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());


        Response response = SendGrid.api(request);

        if (response != null) {
            System.out.println("Response code from SendGrid " + response.getHeaders());
        }

        } catch (IOException e) {
            e.printStackTrace();
            return "Error sending email.";
        }

        return "Email has been sent to " + email + ". Check your email!";

    }

    public Mail prepareMail(String email) {

            Mail mail = prepareMail(email);
            Email fromEmail = new Email();

            fromEmail.setEmail("noreply@aerofood.com");
            Email to = new Email();
            to.setEmail(email);

            Personalization personalization = new Personalization();
            personalization.addTo(to);
            mail.addPersonalization(personalization);
            mail.setTemplateId(templateId);

            return mail;
    }

//    @Autowired
//    public JavaMailSender emailSender;
//
//    @Value("${spring.mail.from}")
//    private String from;
//
//    public void prepareAndSend(User user, String subject, String body) {
//        SimpleMailMessage msg = new SimpleMailMessage();
//        String email = user.getEmail();
//        msg.setFrom(from);
//        msg.setTo();
//        msg.setSubject(subject);
//        msg.setText(body);
//
//        try{
//            this.emailSender.send(msg);
//        }
//        catch (MailException ex) {
//            // simply log it and go on...
//            System.err.println(ex.getMessage());
//        }
//    }
}