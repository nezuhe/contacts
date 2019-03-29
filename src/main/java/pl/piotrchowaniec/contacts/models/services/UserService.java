package pl.piotrchowaniec.contacts.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.piotrchowaniec.contacts.models.forms.LoginForm;
import pl.piotrchowaniec.contacts.models.entities.UserEntity;
import pl.piotrchowaniec.contacts.models.forms.RegistrationForm;
import pl.piotrchowaniec.contacts.models.mappers.RegistrationFormToUserEntityMapper;
import pl.piotrchowaniec.contacts.models.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final HashService hashService;
    private final UserSession userSession;
    private final UserRepository userRepository;

    @Autowired
    public UserService(HashService hashService, UserSession userSession, UserRepository userRepository) {
        this.hashService = hashService;
        this.userSession = userSession;
        this.userRepository = userRepository;
    }

    public void addUser(RegistrationForm registrationForm) {
        registrationForm.setPassword(hashService.hashPassword(registrationForm.getPassword()));
        userRepository.save(new RegistrationFormToUserEntityMapper().map(registrationForm));
    }

    public boolean isLoginFree(String login) {
        return !userRepository.existsByLogin(login);
    }

    public boolean login(LoginForm loginForm) {
        Optional<UserEntity> userWhichTryToLogIn = userRepository.getUserByLogin(loginForm.getLogin());
        if (userWhichTryToLogIn.isPresent() &&
                hashService.isPasswordCorrect(loginForm.getPassword(), userWhichTryToLogIn.get().getPassword())) {
            userSession.setLoggedIn(true);
            userSession.setUserEntity(userWhichTryToLogIn.get());
            return true;
        }
        return false;
    }
}
