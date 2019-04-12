package pl.piotrchowaniec.contacts.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.piotrchowaniec.contacts.models.entities.ContactEntity;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<ContactEntity, Integer> {
    boolean existsByNameAndSurname(String name, String surname);

    boolean existsByPhoneAndUserId(String phone, int id);

    boolean existsByIdAndUserId(int id, int userId);

    @Query(value = "SELECT * FROM `contacts` WHERE `user_id` = ?1", nativeQuery = true)
    List<ContactEntity> getContactByUserId(int userId);
}
