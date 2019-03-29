package pl.piotrchowaniec.contacts.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.piotrchowaniec.contacts.models.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    boolean existsByLogin(String login);

    @Query(value = "SELECT * FROM `users` WHERE `login` = ?", nativeQuery = true)
    Optional<UserEntity> getUserByLogin(String login);
}
