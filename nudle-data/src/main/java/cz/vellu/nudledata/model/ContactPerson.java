package cz.vellu.nudledata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @NotEmpty
    @Size(min=2, max=30)
    private String firstname;

    @NotEmpty
    @Size(min=2, max=30)
    private String surname;

    @NotEmpty
    @Email
    private String email;
    @Pattern(regexp = "\\+[\\d,\\s]{5,25}", message = "Phone number must be in the following format: +123456789")
    private String phone;
    /** address contains street name and number */
    private String address;
    private String city;
    private String region;
    @Column(name = "psc")
    private String postCode;
}
