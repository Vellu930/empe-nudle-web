package cz.vellu.nudleweb;

import cz.vellu.nudleapi.email.ContactsService;
import cz.vellu.nudledata.model.ContactPerson;
import cz.vellu.nudledata.repository.ContactPersonRepository;
import cz.vellu.nudleweb.controller.ContactsController;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Profile("test")
class NudleWeb2ApplicationTests {

	@Autowired
	private MockMvc mvc;
	@Test
	public void loadForm() throws Exception {

		ContactPerson contactPerson = new ContactPerson(12, "M", "L", "email@some.com",
				"456321", "address 123", "London", "Morava", "123654");

		EmailContentModel contentModel = Mockito.mock(EmailContentModel.class);
		when(contentModel.getContactPerson()).thenReturn(contactPerson);
		when(contentModel.getMessage()).thenReturn("Message from NudleWeb2Test...");

		mvc.perform(MockMvcRequestBuilders.get("/home").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
