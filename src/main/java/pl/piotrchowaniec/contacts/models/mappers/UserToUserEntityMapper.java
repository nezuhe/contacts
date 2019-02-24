package pl.piotrchowaniec.contacts.models.mappers;

import org.springframework.stereotype.Service;
import pl.piotrchowaniec.contacts.models.User;
import pl.piotrchowaniec.contacts.models.entities.UserEntity;

@Service
public class UserToUserEntityMapper extends Mapper<User, UserEntity> {

    @Override
    public UserEntity map(User key) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(key.getLogin());
        userEntity.setPassword(key.getPassword());
        userEntity.setRegisterTime(key.getRegisterTime());

        return userEntity;
    }
}
