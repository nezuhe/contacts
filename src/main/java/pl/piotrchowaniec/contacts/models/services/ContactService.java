package pl.piotrchowaniec.contacts.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.piotrchowaniec.contacts.models.forms.ContactForm;
import pl.piotrchowaniec.contacts.models.entities.ContactEntity;
import pl.piotrchowaniec.contacts.models.mappers.ContactToContactEntityMapper;
import pl.piotrchowaniec.contacts.models.repositories.ContactRepository;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final UserSession userSession;

    @Autowired
    public ContactService(ContactRepository contactRepository, UserSession userSession) {
        this.contactRepository = contactRepository;
        this.userSession = userSession;
    }

    public List<ContactEntity> getContacts() {
        return contactRepository.getContactByUserId(userSession.getUserEntity().getId());
    }

    private boolean isPhoneExists(String phone) {
        return contactRepository.existsByPhoneAndUserId(phone, userSession.getUserEntity().getId());
    }

    public boolean isNameAndSurnameExists(ContactForm contactForm) {
        return contactRepository.existsByNameAndSurname(contactForm.getName(), contactForm.getSurname());
    }

    public boolean addContact(ContactForm contactForm) {
        if (isPhoneExists(contactForm.getPhone())) {
            return false;
        } else {
            ContactEntity contactEntity = new ContactToContactEntityMapper().map(contactForm);
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

    private boolean isLoggedUserOwnerOfContact(int id) {
        return contactRepository.existsByIdAndUserId(id, userSession.getUserEntity().getId());
    }
}