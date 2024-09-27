package org.example.blog.services.impls;

import org.example.blog.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendConfirmationEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("estel16@ethereal.email");
        message.setTo("estel16@ethereal.email");
        message.setSubject("Confirmation Email");

        String res = "http://localhost:9090/auth/confirm?email="+email+"&token="+token;
        message.setText(res);

        javaMailSender.send(message);
    }
}
