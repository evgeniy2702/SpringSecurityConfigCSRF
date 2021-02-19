package zhurenko.ua.security;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import zhurenko.ua.model.User;
import zhurenko.ua.repository.UserJPA;
import zhurenko.ua.service.UserService;

import java.util.ArrayList;
import java.util.List;


@Component
public class AuthProvider implements AuthenticationProvider {

//    private final UserService userService;
    private UserJPA userJPA;

    private PasswordEncoder passwordEncoder;

    public void setUserJPA(UserJPA userJPA) {
        this.userJPA = userJPA;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        User user = userJPA.getByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User with this email not found !");
        }

        String password = authentication.getCredentials().toString();
        if(! passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Bad credentials!");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
