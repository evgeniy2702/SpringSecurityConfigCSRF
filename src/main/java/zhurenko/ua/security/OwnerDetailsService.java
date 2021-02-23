package zhurenko.ua.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhurenko.ua.model.Owner;
import zhurenko.ua.service.OwnerService;

@Service
public class OwnerDetailsService implements UserDetailsService {

    private OwnerService ownerService;

    @Autowired
    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Owner owner = ownerService.getOwnerByEmail(email);

        if(owner == null){
            throw new UsernameNotFoundException(String.format("Owner with username %s not found !!!", email));
        }

        return new OwnerDetails(owner);
    }
}
