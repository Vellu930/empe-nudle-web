package cz.vellu.nudleweb.configuration;

import cz.vellu.nudleapi.email.ContactsService;
import cz.vellu.nudleapi.email.MailerService;
import cz.vellu.nudleapi.email.MailerServiceProperties;
import cz.vellu.nudledata.model.ContactPerson;
import cz.vellu.nudledata.repository.ContactPersonRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableJpaRepositories(basePackageClasses = cz.vellu.nudledata.repository.ContactPersonRepository.class)
@EntityScan(basePackageClasses = ContactPerson.class)
@ConfigurationProperties(prefix = "spring.mail")
public class NudleWebConfig {

    @Bean
    public ContactsService contactsService(ContactPersonRepository contactPersonRepository) {
        return new ContactsService(contactPersonRepository);
    }

    @Bean
    @ConfigurationProperties("email.info")
    public MailerServiceProperties mailerProps() {
        return new MailerServiceProperties();
    }

    @Bean
    public MailerService mailerService(MailerServiceProperties mailerServiceProperties, JavaMailSender mailSender) {
        return new MailerService(mailerServiceProperties, mailSender);
    }

    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        return templateResolver;
    }
    @Bean
    public ISpringTemplateEngine tempEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

}
