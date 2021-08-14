
package cz.vellu.nudle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailerService {
    
    @Autowired
    private EmailConfig config;
    
    public MailerService(EmailConfig config) {
        this.config = config;
    }
    
    public void sendEmail(String message,String subject, String from, String to) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(this.config.getHost());
        sender.setPort(this.config.getPort());
        sender.setUsername(this.config.getUser());
        sender.setPassword(this.config.getPass());

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(message);
        sender.send(mail);
    }
}
