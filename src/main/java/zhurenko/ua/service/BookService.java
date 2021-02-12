package zhurenko.ua.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhurenko.ua.model.Book;
import zhurenko.ua.model.Owner;
import zhurenko.ua.repository.BookJPA;
import zhurenko.ua.repository.OwnerJPA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class BookService {

    private final BookJPA bookJPA;
    private final OwnerJPA ownerJPA;

    @Autowired
    public BookService(BookJPA bookJPA, OwnerJPA ownerJPA) {
        this.bookJPA = bookJPA;
        this.ownerJPA = ownerJPA;
    }

    public void saveBook(Book book){
        bookJPA.save(book);
        bookJPA.flush();
    }

    public void deleteBook(Book book){
        bookJPA.delete(book);
        bookJPA.flush();
    }

    public void updateBook(Book book){
        bookJPA.saveAndFlush(book);
    }

    public Book getByIdBook(Long id){
        return bookJPA.getOne(id);
    }

    public List<Book> getAllBooks() {
        return bookJPA.findAll();
    }

    public <T> List<Book> searchBook(T search) {

        List<Book> books = new ArrayList<>();
        if( search instanceof String ) {
            if (bookJPA.findBookByName((String)search).size() != 0)
                books = bookJPA.findBookByName((String) search);
            if (bookJPA.findBookByAuthor((String)search).size() != 0)
                books = bookJPA.findBookByAuthor((String)search);
            if (bookJPA.findBookByStileOfBook((String) search).size() != 0)
                books = bookJPA.findBookByStileOfBook((String) search);
            if (bookJPA.findBookByDescription((String) search).size()!= 0)
                books = bookJPA.findBookByDescription((String) search);
        } else if (search instanceof Integer) {
            if (bookJPA.findBookByYear((Integer) search).size() != 0)
                books = bookJPA.findBookByYear((Integer) search);
            if (bookJPA.findBookByNumPages((Integer) search).size()!= 0)
                books = bookJPA.findBookByNumPages((Integer) search);
        }
        return books;
    }

    public Set<Owner> getOwners() {
        return new HashSet<>(ownerJPA.findAll());
    }
}
