package cz.vellu.nudle;

import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author vellu
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private MailerService mailer;

    @GetMapping
    public String greeting(@ModelAttribute Form form, Model model) {
        model.addAttribute("form", form);
        return "home";
    }

    @PostMapping
    private String submitForm(@ModelAttribute Form form,Model model, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException("Validation exception");
        }
        String mailTo = "5580f25f35-0092af@inbox.mailtrap.io";
        String mailFrom = form.getEmail();
        String name = form.getFirstName() + " " + form.getSurname();
        String region = form.getRegion();
        String message = form.getMessage();
        mailer.sendEmail(message, "New message from "+name+ ", region: "+region, mailFrom, mailTo);
        model.addAttribute("success", "Email sent! Thank you!");
        return "home";
    }
}
