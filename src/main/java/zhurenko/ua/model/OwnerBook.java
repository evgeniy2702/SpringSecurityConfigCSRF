package zhurenko.ua.model;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name = "owner_books")
public class OwnerBook {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "book_id")
    private Long book_id;

    @Column(name = "owner_id")
    private Long owner_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "{" + book_id + ";" + owner_id + "}";
    }
}
