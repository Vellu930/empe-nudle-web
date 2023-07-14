package cz.vellu.nudleweb.controller;

import cz.vellu.nudleapi.email.ContactsService;
import cz.vellu.nudledata.model.ContactPerson;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ContactsController {

    private ContactsService contactsService;

    @GetMapping("/contact/{id}")
    public ContactPerson findContactPersonById(@PathVariable int id) {
        return contactsService.findById(id).orElse(new ContactPerson());
    }

    @PostMapping("/contact/save")
    public ResponseEntity<ContactPerson> saveNewContact(@RequestBody ContactPerson contact) {
        contactsService.saveContact(contact);
        return new ResponseEntity<ContactPerson>(contact, HttpStatus.CREATED);
    }


}
