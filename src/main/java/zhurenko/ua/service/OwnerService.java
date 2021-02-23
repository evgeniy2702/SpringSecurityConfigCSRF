package zhurenko.ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zhurenko.ua.model.Owner;
import zhurenko.ua.model.Role;
import zhurenko.ua.repository.OwnerJPA;

import java.util.Collections;
import java.util.List;

@Service
public class OwnerService {

    private final OwnerJPA ownerJPA;

    private PasswordEncoder passwordEncoder;

    public OwnerService(OwnerJPA ownerJPA) {
        this.ownerJPA = ownerJPA;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<Owner> getListOwners() {
        return ownerJPA.findAll();
    }

    public boolean saveOwner(Owner owner) {
        Owner ownerFromDB = ownerJPA.getByEmail(owner.getEmail());
        if(ownerFromDB != null){
            return false;
        }
        owner.setRoles(Collections.singleton(new Role(1L, "USER")));
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        ownerJPA.saveAndFlush(owner);
        return true;
    }


    public Owner getOwnerById(Long id){
        return ownerJPA.getOne(id);
    }

    public Owner getOwnerByEmail(String email) {
        return ownerJPA.getByEmail(email);
    }
}
