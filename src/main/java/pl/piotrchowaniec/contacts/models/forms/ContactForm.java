package pl.piotrchowaniec.contacts.models.forms;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class ContactForm {

    @Pattern(regexp = "[A-Za-z]{3,30}", message = "Imię może zawierać tylko litery i mieć długość od 3 do 30 znaków")
    private String name;
    @Pattern(regexp = "[A-Za-z]{3,30}", message = "Nazwisko może zawierać tylko litery i mieć długość od 3 do 30 znaków")
    private String surname;
    @Pattern(regexp = "[0-9]{9}", message = "Numer telefonu musi zawierać 9 cyfr")
    private String phone;
    @Email
    private String email;
}
