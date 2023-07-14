package cz.vellu.nudleapi.email;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 *  Email info and template configuration values for {@link MailerService}
 */
@Data
@Validated
public class MailerServiceProperties {
    private String subject;
    @NotNull
    private String receiverEmail;
    @NotNull
    private String emailTemplate;
    private String encoding;
}
