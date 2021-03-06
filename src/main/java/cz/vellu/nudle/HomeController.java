package cz.vellu.nudle;

import javax.validation.Valid;
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
@RequestMapping("/")
public class HomeController {

    @Autowired
    private MailerService mailer;

    @GetMapping
    public String loadForm(@ModelAttribute Form form, Model model) {
        model.addAttribute("form", form);
        return "home";
    }

    @PostMapping("/home")
    private String submitForm(@ModelAttribute @Valid Form form, Model model, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException("Validation exception");
        }
        String mailTo = "vellaipuraaa@gmail.com";
        String mailFrom = form.getEmail();
        String name = form.getFirstName() + " " + form.getSurname();
        String phone = form.getPhone();
        String region = form.getRegion();
        String city = form.getCity();
        String address = form.getAddress();
        String psc = form.getPsc();
        String message = form.getMessage();
        mailer.sendEmail(message, name, mailFrom, mailTo, phone, address, city, region, psc);
        model.addAttribute("success", "Email odeslan. Dekuji!");

        return "home";
    }
}
