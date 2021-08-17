package cz.vellu.nudle;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailerService {

    @Autowired
    private EmailConfig config;

    public MailerService(EmailConfig config) {
        this.config = config;
    }

    @Autowired
    private TemplateEngine tempEngine;

    private String message;
    private String name;
    private String adresa;
    private String mesto;
    private String region;
    private String telefon;
    private String email;
    private String psc;

    public String build() {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("name", name);
        context.setVariable("adresa", adresa);
        context.setVariable("mesto", mesto);
        context.setVariable("region", region);
        context.setVariable("telefon", telefon);
        context.setVariable("email", email);
        context.setVariable("psc", psc);
        return tempEngine.process("email-content.html", context);
    }

    public void sendEmail(String message,
            String name, String from, String to, String telefon, String adresa,
            String mesto, String kraj, String psc) {
        this.message = message;
        this.name = name;
        this.adresa = adresa;
        this.mesto = mesto;
        this.region = kraj;
        this.telefon = telefon;
        this.psc = psc;
        this.email = from;

        try {
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            sender.setHost(this.config.getHost());
            sender.setPort(this.config.getPort());
            sender.setUsername(this.config.getUser());
            sender.setPassword(this.config.getPass());
            Properties prop = sender.getJavaMailProperties();
            prop.put("mail.smtp.starttls.enable", "true");

            MimeMessage mimeMessage = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setSubject("Nova zprava od: " + name);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setText(build(), true);

            sender.send(mimeMessage);

        } catch (MessagingException ex) {
            Logger.getLogger(MailerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
