package zhurenko.ua.service;

import org.springframework.stereotype.Service;
import zhurenko.ua.model.Owner;
import zhurenko.ua.repository.OwnerJPA;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    private final OwnerJPA ownerJPA;

    public OwnerService(OwnerJPA ownerJPA) {
        this.ownerJPA = ownerJPA;
    }


    public List<Owner> getListOwners() {
        return ownerJPA.findAll();
    }

    public void saveOwner(Owner owner) {
        ownerJPA.saveAndFlush(owner);
    }

    public void updateOwner(Owner owner){
        ownerJPA.saveAndFlush(owner);
    }

    public Owner getLastOwnerByName(String string) {
        List<Owner> owners = ownerJPA.findAll();
        owners = owners.stream().filter(o -> o.getNameOwner().equalsIgnoreCase(string)).collect(Collectors.toList());
        return (Owner) owners.stream().sorted((o1, o2) -> o2.getId().compareTo(o1.getId())).toArray()[0];
    }

    public Owner getOwnerById(Long id){
        return ownerJPA.getOne(id);
    }
}
