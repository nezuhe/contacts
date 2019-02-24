package pl.piotrchowaniec.contacts.models.services;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashService {

    public String hashPassword(String password) {
        return getBCrypt().encode(password);
    }

    public boolean isPasswordCorrect(String typed, String hashed) {
        return getBCrypt().matches(typed, hashed);
    }

    @Bean
    public BCryptPasswordEncoder getBCrypt() {
        return new BCryptPasswordEncoder();
    }
}
