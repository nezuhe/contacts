package pl.piotrchowaniec.contacts.models.forms;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class AddContactForm {
    @Pattern(regexp = "[A-Za-z]{3,30}")
    private String name;
    @Pattern(regexp = "[A-Za-z]{3,30}")
    private String surname;
    @Pattern(regexp = "[0-9]{9}")
    private String phone;
    @Email
    private String email;
}
