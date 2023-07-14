package cz.vellu.nudleweb;

import cz.vellu.nudledata.model.ContactPerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data model for creating email. Represents data collected by the page contact form.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailContentModel {

    private ContactPerson contactPerson;

    private String message;

}
