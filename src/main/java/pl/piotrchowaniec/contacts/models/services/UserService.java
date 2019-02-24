package pl.piotrchowaniec.contacts.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.piotrchowaniec.contacts.models.User;
import pl.piotrchowaniec.contacts.models.entities.UserEntity;
import pl.piotrchowaniec.contacts.models.mappers.UserToUserEntityMapper;
import pl.piotrchowaniec.contacts.models.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    final HashService hashService;
    final UserSession userSession;
    final UserRepository userRepository;

    @Autowired
    public UserService(HashService hashService, UserSession userSession, UserRepository userRepository) {
        this.hashService = hashService;
        this.userSession = userSession;
        this.userRepository = userRepository;
    }

    public boolean addUser(User user) {
        if (!isLoginFree(user.getLogin())) {
            return false;
        }
        user.setPassword(hashService.hashPassword(user.getPassword()));
        return userRepository.save(new UserToUserEntityMapper().map(user)) != null;
//        return true;
    }

    public boolean isPasswordConfirmed(User user) {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            return false;
        }
        return true;
    }

    public boolean isLoginFree(String login) {
        return !userRepository.existsByLogin(login);
    }

    public boolean login(User user) {
        Optional<UserEntity> userWhichTryToLogIn = userRepository.getUserByLogin(user.getLogin());

        if (userWhichTryToLogIn.isPresent() &&
                hashService.isPasswordCorrect(user.getPassword(), userWhichTryToLogIn.get().getPassword())) {
            userSession.setLoggedIn(true);
            userSession.setUserEntity(userWhichTryToLogIn.get());
            return true;
        }

        return false;
    }
}
