package zhurenko.ua.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import zhurenko.ua.model.Owner;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OwnerDetails extends Owner implements UserDetails {

    public OwnerDetails(Owner owner){
        this.setId(owner.getId());
        this.setNameOwner(owner.getNameOwner());
        this.setEmail(owner.getEmail());
        this.setPassword(owner.getPassword());
        this.setRoles(owner.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = super.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
