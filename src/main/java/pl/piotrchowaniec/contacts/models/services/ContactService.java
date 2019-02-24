package pl.piotrchowaniec.contacts.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.piotrchowaniec.contacts.models.forms.AddContactForm;
import pl.piotrchowaniec.contacts.models.entities.ContactEntity;
import pl.piotrchowaniec.contacts.models.mappers.ContactToContactEntityMapper;
import pl.piotrchowaniec.contacts.models.repositories.ContactRepository;

import java.util.List;

@Service
public class ContactService {

    final ContactRepository contactRepository;
    final UserSession userSession;

    @Autowired
    public ContactService(ContactRepository contactRepository, UserSession userSession) {
        this.contactRepository = contactRepository;
        this.userSession = userSession;
    }

    public List<ContactEntity> getContacts() {
        return contactRepository.getContactByUserId(userSession.getUserEntity().getId());
    }

    public boolean isPhoneExists(String phone) {
        if (contactRepository.existsByPhoneAndUserId(phone, userSession.getUserEntity().getId())) {
            return true;
        }
        return false;
    }

    public boolean isNameAndSurnameExists(AddContactForm addContactForm) {
        if (contactRepository.existsByNameAndSurname(addContactForm.getName(), addContactForm.getSurname())) {
            return true;
        }
        return false;
    }

    public boolean addContact(AddContactForm addContactForm) {
        if (isPhoneExists(addContactForm.getPhone())) {
            return false;
        } else {
            ContactEntity contactEntity = new ContactToContactEntityMapper().map(addContactForm);
            contactEntity.setUser(userSession.getUserEntity());
            contactRepository.save(contactEntity);
            return true;
        }
    }

    public void deleteContact(int id) {
        if (!userSession.isLoggedIn()) {
            return;
        }
        if (!isLoggedUserOwnerOfContact(id)) {
            return;
        }

        contactRepository.deleteById(id);
    }

    public boolean isLoggedUserOwnerOfContact(int id) {
        if (contactRepository.existsByIdAndUserId(id, userSession.getUserEntity().getId())) {
            return true;
        }
        return false;
    }
}