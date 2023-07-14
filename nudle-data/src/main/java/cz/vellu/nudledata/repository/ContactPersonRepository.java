package cz.vellu.nudledata.repository;

import cz.vellu.nudledata.model.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactPersonRepository extends JpaRepository<ContactPerson, Integer> {

    Optional<ContactPerson> findBySurname(String surname);

    List<ContactPerson> findContactPersonBySurname(String surname);

    List<ContactPerson> findPeopleByCity(String city);


}
