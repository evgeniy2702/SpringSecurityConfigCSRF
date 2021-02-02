package zhurenko.ua.service;

import zhurenko.ua.model.Book;
import zhurenko.ua.model.Owner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface BookInterface {

    void saveBook(Book book);

    void deleteBook(Book book);

    void updateBook(Book book);

    Book getByIdBook(Long id);

    List<Book> getAllBooks();

    List<Book> searchBook(String search);

    Set<Owner> getOwners();

}
