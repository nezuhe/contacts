package pl.piotrchowaniec.contacts.models.mappers;

import org.springframework.stereotype.Service;
import pl.piotrchowaniec.contacts.models.forms.AddContactForm;
import pl.piotrchowaniec.contacts.models.entities.ContactEntity;

@Service
public class ContactToContactEntityMapper extends Mapper<AddContactForm, ContactEntity> {

    @Override
    public ContactEntity map(AddContactForm key) {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setName(key.getName());
        contactEntity.setSurname(key.getSurname());
        contactEntity.setPhone(key.getPhone());
        contactEntity.setEmail(key.getEmail());

        return contactEntity;
    }
}
