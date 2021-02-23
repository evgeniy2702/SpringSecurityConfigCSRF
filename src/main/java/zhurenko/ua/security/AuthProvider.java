package zhurenko.ua.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import zhurenko.ua.model.Owner;
import zhurenko.ua.service.OwnerService;

import java.util.ArrayList;
import java.util.List;


@Component
public class AuthProvider implements AuthenticationProvider {

//    private final UserService userService;
    private OwnerService ownerService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        Owner owner = ownerService.getOwnerByEmail(email);
        if(owner == null){
            throw new UsernameNotFoundException(String.format("User with email %s not found !", email));
        }

        String password = authentication.getCredentials().toString();
        if(! passwordEncoder.matches(password, owner.getPassword())){
            throw new BadCredentialsException(String.format("Bad credentials! Password %s is absent", password));
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new UsernamePasswordAuthenticationToken(owner, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
