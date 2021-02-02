package zhurenko.ua.service;

import zhurenko.ua.model.Book;
import zhurenko.ua.model.Owner;

import java.util.List;
import java.util.Set;

public interface BookInterface {

    void saveBook(Book book);

    void deleteBook(Book book);

    void updateBook(Book book);

    Book getByIdBook(Long id);

    List<Book> getAllBooks();

    <T> List<Book> searchBook( T search);

    Set<Owner> getOwners();
}
