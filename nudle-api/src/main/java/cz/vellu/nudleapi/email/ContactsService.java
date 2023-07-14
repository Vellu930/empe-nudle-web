package cz.vellu.nudleapi.email;

import cz.vellu.nudledata.model.ContactPerson;
import cz.vellu.nudledata.repository.ContactPersonRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Optional;

@AllArgsConstructor
public class ContactsService {

    private ContactPersonRepository repository;

    /**
     * method to save contact person to DB,
     * @Param contact
     */
    public void saveContact(@NonNull ContactPerson contact) {
        repository.save(contact);
    }

    public Optional<ContactPerson> findById(int id) {
        return repository.findById(id);
    }
}
