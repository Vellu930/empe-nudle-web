package cz.vellu.nudleweb.util;

import cz.vellu.nudledata.model.ContactPerson;
import cz.vellu.nudleweb.EmailContentModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.spring5.ISpringTemplateEngine;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Profile("test")
class TemplateProcessorTest {

    @Autowired
    ISpringTemplateEngine tempEngine;
    /**
     * Test if the 'email-content.html' template content is rendered correctly.
     */
    @Test
    void renderEmailTemplate() {

        ContactPerson person = new ContactPerson(12, "M", "L", "email@some.com",
                "456321", "address 123", "London", "Morava", "123654");
        EmailContentModel form = new EmailContentModel(person, "Hello Spring!");
        String template =  this.tempEngine.process("email-content.html", TemplateProcessor.buildContext(form, "formModel"));

        String expectedHtml = """
                <!DOCTYPE html>
                <html>
                    <head>
                        <title></title>
                        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                    </head>
                    <body>
                        <div>
                            <p>Dobry den!</p>
                            <p> Mate novou zpravu od zákazníka: <b>M L</b> </p> <br/>
                            <h4>Hello Spring!</h4>
                            <u> Kontakt na zakaznika: </u> <br/>
                            <span>adresa: address 123, London, 123654</span> <br/>
                            <span>region: Morava</span> <br/>
                            <span>telefon: 456321</span> <br/>
                            <span>email: email@some.com</span><br/>
                        </div>
                                
                        <p>
                            Zdravi, <br />
                            <em>Spring Mailer Service</em>
                        </p>
                                
                        <p>
                            <b>S pozdravem EMPE nudle <br/> </b>
                            navstivte nas EMPE web: <br/>
                            <a href="empenudle.herokuapp.com"> empenudle.herokuapp.com </a>
                        </p>
                    </body>
                </html>""";

        assertEquals(template, expectedHtml);
    }

}