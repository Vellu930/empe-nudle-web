package cz.vellu.nudleweb.controller;

import cz.vellu.nudleapi.email.MailerService;
import cz.vellu.nudleapi.email.MailerServiceProperties;
import cz.vellu.nudleweb.EmailContentModel;
import cz.vellu.nudleweb.util.TemplateProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.ISpringTemplateEngine;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping
@AllArgsConstructor
public class HomeController implements WebMvcConfigurer {

    private MailerService mailer;
    private MailerServiceProperties mailerServiceProperties;
    private ISpringTemplateEngine tempEngine;

    @GetMapping("/")
    public String loadForm(EmailContentModel formModel, Model model) {
        model.addAttribute("formModel", formModel);
        return "home";
    }

    @PostMapping("/order")
    public String submitForm(@Valid @ModelAttribute("formModel") EmailContentModel emailModel, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "home";
        }

        try {
            sendEmail(emailModel);
        } catch (MessagingException | MailSendException ex) {
            log.warn("Error during sending email: {}", emailModel.getContactPerson().getEmail(), ex);
            model.addAttribute("formModel", emailModel);
            return "redirect:/failure#form";
        }

        model.addAttribute("formModel", new EmailContentModel());
        return "redirect:/success#form";
    }

    /**
     * sends the actual email
     *
     * @param emailModel contact form data
     */
    private void sendEmail(EmailContentModel emailModel) throws MessagingException, MailSendException {
        long start = System.currentTimeMillis();
        mailer.sendEmail(emailModel.getContactPerson(),
                tempEngine.process(mailerServiceProperties.getEmailTemplate(), TemplateProcessor.buildContext(emailModel, "formModel")));
        log.info("Email sending took {} ms.", System.currentTimeMillis() - start);
    }

    @GetMapping("/success")
    public String successPage(EmailContentModel formModel, Model model) {
        model.addAttribute("formModel", formModel);
        model.addAttribute("success", "Email odeslan. Dekuji!");
        return "home";
    }

    @GetMapping("/failure")
    public String failurePage(EmailContentModel formModel, Model model) {
        model.addAttribute("formModel", formModel);
        model.addAttribute("failure", "Email se nepodarilo odeslat, zkuste to pozdeji.");
        return "home";
    }

}
