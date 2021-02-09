package zhurenko.ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zhurenko.ua.model.Book;
import zhurenko.ua.model.Owner;
import zhurenko.ua.service.BookOwnerService;
import zhurenko.ua.service.BookService;
import zhurenko.ua.service.OwnerService;
import java.util.HashSet;

@Controller
public class BookController {

    private final BookService bookService;
    private final BookOwnerService bookOwnerService;
    private final OwnerService ownerService;

    public BookController(BookService bookService, BookOwnerService bookOwnerService, OwnerService ownerService) {
        this.bookService = bookService;
        this.bookOwnerService = bookOwnerService;
        this.ownerService = ownerService;
    }

    @GetMapping("")
    public String showBook(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "showBooks";
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable(value = "id", required = false) Long id,
                          Model model)  {
        Book book = bookService.getByIdBook(id);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/book/new")
    public String addBook(Model model) {
        Book book = new Book();
        book.setOwners(new HashSet<>(bookService.getOwners().size()));
        model.addAttribute("book", book);
        model.addAttribute("default", "Default value");
        return "addBook";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable(value = "id", required = false) Long id,
                             Model model){
        Book book = bookService.getByIdBook(id);
        bookService.deleteBook(book);
        model.addAttribute("books", bookService.getAllBooks());
        return "redirect:/";
    }

    @PostMapping("/book/add")
    public String saveBook(@ModelAttribute Book book){
        bookService.saveBook(book);
        return "redirect:/";
    }

    @PostMapping("/book/update/{id}")
    public String updateBook(@PathVariable(value = "id", required = false) Long id,
                             @ModelAttribute Book book){
        bookService.updateBook(book);
        return "redirect:/";
    }

    @GetMapping("/book/delete/relation")
    public String updateBookWithoutOwner(@RequestParam("book_id") Long book_id,
                                         @RequestParam("owner_id") Long owner_id){
        bookOwnerService.deleteRelation(book_id,owner_id);
        return "redirect:/";
    }

    @GetMapping("/book/add/owner")
    public String addOwner (@RequestParam("id") Long book_id,
                            @RequestParam("owner_book") String string){
        if (ownerService.getListOwners().stream().noneMatch(o -> o.getNameOwner().equalsIgnoreCase(string))) {
            Owner owner = new Owner();
            owner.setNameOwner(string);
            ownerService.saveOwner(owner);
            bookOwnerService.saveRelation(book_id, owner.getId());
        } else {
            Owner owner = (Owner)ownerService.getListOwners().stream()
                    .filter(o -> o.getNameOwner().equalsIgnoreCase(string))
                    .toArray()[0];
            bookOwnerService.saveRelation(book_id, owner.getId());
        }
        return "redirect:/";
    }

    @GetMapping("/book/update/{id}")
    public String showUpdateBook(@PathVariable(value = "id", required = false) Long id,
                                 Model model){
        model.addAttribute("book",bookService.getByIdBook(id));
        return "updateBook";
    }

    @GetMapping("/book/search")
    public String search(@ModelAttribute Book book){
        return "searchBook";
    }

    @GetMapping("/books")
    public String searchBook(@RequestParam(value = "search", required = false) String search,
                             Model model){
        model.addAttribute("booksSearch", bookService.searchBook(search));
        return "searchBook";
    }
}
