package pl.piotrchowaniec.contacts.models.mappers;

import org.springframework.stereotype.Service;
import pl.piotrchowaniec.contacts.models.entities.UserEntity;
import pl.piotrchowaniec.contacts.models.forms.RegistrationForm;

@Service
public class RegistrationFormToUserEntityMapper extends Mapper<RegistrationForm, UserEntity> {

    @Override
    public UserEntity map(RegistrationForm key) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(key.getLogin());
        userEntity.setPassword(key.getPassword());
        userEntity.setRegisterTime(key.getRegisterTime());
        userEntity.setAccountStatus(UserEntity.AccountStatus.NOT_ACTIVATED);
        return userEntity;
    }
}
