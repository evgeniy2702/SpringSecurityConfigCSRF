package zhurenko.ua.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_owner")
    private Long id;
    @Column(name = "name")
    private String nameOwner;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "owners")
    private Set<Book> bookOwnerSet ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public Set<Book> getBookOwnerSet() {
        return bookOwnerSet;
    }

    public void setBookOwnerSet(Set<Book> bookOwnerSet) {
        this.bookOwnerSet = bookOwnerSet;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", nameOwner='" + nameOwner + '\'' +
                '}';
    }
}
