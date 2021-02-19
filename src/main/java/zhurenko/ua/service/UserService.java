package zhurenko.ua.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zhurenko.ua.model.User;
import zhurenko.ua.repository.UserJPA;

@Service
public class UserService implements UserDetailsService{

    private UserJPA userJPA;

    private BCryptPasswordEncoder passwordEncoder;

    public void setUserJPA(UserJPA userJPA) {
        this.userJPA = userJPA;
    }

    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserById(Long id){
        return userJPA.getById(id);
    }

    public User getUserByEmail(String email){
        return userJPA.getByEmail(email);
    }

    public void save(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userJPA.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
