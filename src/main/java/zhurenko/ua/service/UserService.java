package zhurenko.ua.service;

import org.springframework.stereotype.Service;
import zhurenko.ua.model.User;
import zhurenko.ua.repository.UserJPA;

@Service
public class UserService {

    private final UserJPA userJPA;

    public UserService(UserJPA userJPA) {
        this.userJPA = userJPA;
    }

    public User getUserById(Long id){
        return userJPA.getById(id);
    }

    public User getUserByEmail(String email){
        return userJPA.getByEmail(email);
    }
}
