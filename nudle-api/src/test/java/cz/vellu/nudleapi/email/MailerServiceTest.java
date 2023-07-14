package cz.vellu.nudleapi.email;

import cz.vellu.nudledata.model.ContactPerson;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

class MailerServiceTest {

    @Test
    void shouldSendEmail() {
        JavaMailSender sender = mock(JavaMailSender.class);
        MailerServiceProperties mailerProperties = mock(MailerServiceProperties.class);
        MailerService mailerService = new MailerService(mailerProperties, sender);
        ContactPerson person = new ContactPerson(12, "M", "L", "email@some.com",
                "456321", "address 123", "London", "Morava", "123654");
        String shortTemplate = "<html> <p> <b>Test template paragraph bold? </b> <p> yey! </html>";

        assertDoesNotThrow(() -> mailerService.sendEmail(person, shortTemplate));
    }

}