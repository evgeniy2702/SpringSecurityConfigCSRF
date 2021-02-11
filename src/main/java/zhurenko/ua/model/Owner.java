package zhurenko.ua.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "owners")
@Proxy(lazy = false)
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_owner")
    private Long id;
    @Column(name = "name")
    private String nameOwner;

    @ManyToMany(fetch = FetchType.EAGER,
            mappedBy = "owners")
//    @JoinTable(name = "owner_books",
//            joinColumns =  @JoinColumn(name = "owner_id" , referencedColumnName = "id_owner"),
//            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
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

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", nameOwner='" + nameOwner + '\'' +
                '}';
    }
}
