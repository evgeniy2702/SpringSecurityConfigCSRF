package zhurenko.ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhurenko.ua.hebirnate.HibernateBookDAO;
import zhurenko.ua.model.Book;
import zhurenko.ua.model.Owner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class BookService implements BookInterface {

    private final HibernateBookDAO hibernateBookDAO;

    @Autowired
    public BookService(HibernateBookDAO hibernateBookDAO) {
        this.hibernateBookDAO = hibernateBookDAO;
    }

    @Override
    public void saveBook(Book book){
        hibernateBookDAO.addBook(book);
    }

    @Override
    public void deleteBook(Book book){
        hibernateBookDAO.deleteBook(book);
    }

    @Override
    public void updateBook(Book book){
        hibernateBookDAO.updateBook(book);
    }

    @Override
    public Book getByIdBook(Long id){
        return hibernateBookDAO.getBook(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return hibernateBookDAO.getAllBooks();
    }

    @Override
    public List<Book> searchBook(String search) {
        return hibernateBookDAO.searchBook(search);
    }

    @Override
    public Set<Owner> getOwners() {
        return hibernateBookDAO.getOwner();
    }
}
