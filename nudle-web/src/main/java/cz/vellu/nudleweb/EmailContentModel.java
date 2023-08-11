package cz.vellu.nudleweb;

import cz.vellu.nudledata.model.ContactPerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Data model for creating email. Represents data collected by the page contact form.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailContentModel {

    @Valid
    private ContactPerson contactPerson;

    @NotEmpty
    @Size(min=5, max = 5000)
    private String message;

}
