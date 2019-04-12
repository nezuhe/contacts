package pl.piotrchowaniec.contacts.models.mappers;

import org.springframework.stereotype.Service;
import pl.piotrchowaniec.contacts.models.forms.ContactForm;
import pl.piotrchowaniec.contacts.models.entities.ContactEntity;

@Service
public class ContactToContactEntityMapper extends Mapper<ContactForm, ContactEntity> {

    @Override
    public ContactEntity map(ContactForm key) {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setName(key.getName());
        contactEntity.setSurname(key.getSurname());
        contactEntity.setPhone(key.getPhone());
        contactEntity.setEmail(key.getEmail());

        return contactEntity;
    }
}
