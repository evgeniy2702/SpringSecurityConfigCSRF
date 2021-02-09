package zhurenko.ua.service;

import org.springframework.stereotype.Service;
import zhurenko.ua.model.Book;
import zhurenko.ua.model.Owner;
import zhurenko.ua.model.OwnerBook;
import zhurenko.ua.repository.BookJPA;
import zhurenko.ua.repository.BookOwnerJPA;
import zhurenko.ua.repository.OwnerJPA;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookOwnerService {

    private final BookOwnerJPA bookOwnerJPA;
    private final OwnerJPA ownerJPA;
    private final BookJPA bookJPA;

    public BookOwnerService(BookOwnerJPA bookOwnerJPA, OwnerJPA ownerJPA, BookJPA bookJPA) {
        this.bookOwnerJPA = bookOwnerJPA;
        this.ownerJPA = ownerJPA;
        this.bookJPA = bookJPA;
    }

    public void saveRelation(Long bookId, Long ownerId) {
        Book book = bookJPA.getOne(bookId);
        Owner owner = ownerJPA.getOne(ownerId);
        if(!book.getOwners().contains(owner) && !owner.getBookOwnerSet().contains(book)) {
            OwnerBook ownerBook = new OwnerBook();
            ownerBook.setBook_id(bookId);
            ownerBook.setOwner_id(ownerId);
            bookOwnerJPA.save(ownerBook);
        }
    }

    public void deleteRelation(Long bookId, Long ownerId) {
        List<OwnerBook> ownerBooks = bookOwnerJPA.findAll();
        ownerBooks.forEach(System.out::println);
        OwnerBook ownerBook = (OwnerBook)ownerBooks.stream().filter(o -> o.getBook_id().equals(bookId))
                                .collect(Collectors.toList()).stream()
                                .filter(o -> o.getOwner_id().equals(ownerId)).toArray()[0];
        bookOwnerJPA.delete(ownerBook);
    }
}
