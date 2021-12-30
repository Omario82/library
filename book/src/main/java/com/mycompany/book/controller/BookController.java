package book.controller;

import book.domain.model.Book;
import book.service.BookDAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookDAService bookDAService;

    public BookController(BookDAService bookDAService){
        this.bookDAService = bookDAService;
    }

    public BookController(){ }

    @GetMapping("/books")
    public List<Book> allAuthors() {
        return bookDAService.getAll();
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addAuthor(@RequestBody Book author) {
        return bookDAService.save(author);
    }

    @GetMapping("/books/{id}")
    public List<Book> findById(@PathVariable int id) {
        return bookDAService.findById(id);
    }
}
