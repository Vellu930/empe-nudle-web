package cz.vellu.nudleapi.email;

import cz.vellu.nudledata.model.ContactPerson;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;

/**
 * Emailing service
 */
@Slf4j
@RequiredArgsConstructor
public class MailerService {

    @NonNull
    private final MailerServiceProperties properties;

    @Autowired
    private final JavaMailSender sender;

    /**
     * configures and sends email using data from a contact form
     *
     * @param contactPerson person who filled the contact form - set as sender
     * @param emailTemplate email html template
     */
    public void sendEmail(@NonNull ContactPerson contactPerson, @NonNull String emailTemplate) throws MessagingException, MailSendException {

        sender.send(mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, this.properties.getEncoding());
            helper.setSubject(String.format(this.properties.getSubject(),
                    contactPerson.getFirstname(), contactPerson.getSurname()));
            helper.setFrom(contactPerson.getEmail());
            helper.setTo(this.properties.getReceiverEmail());
            helper.setText(emailTemplate, true);
        });
    }

}
