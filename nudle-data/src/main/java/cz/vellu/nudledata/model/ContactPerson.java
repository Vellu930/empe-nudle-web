package cz.vellu.nudledata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "contact")
public class ContactPerson {

    @NotNull
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private int id;

    @NotNull
    private String firstname;

    @NotNull
    private String surname;

    @NotNull
    @Email
    private String email;
    private String phone;
    /** address contains street name and number */
    private String address;
    private String city;
    private String region;
    private String psc;
}
