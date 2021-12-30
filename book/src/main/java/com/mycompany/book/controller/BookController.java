package com.mycompany.book.controller;

import com.mycompany.book.domain.logic.IEntityDAService;
import com.mycompany.book.domain.model.Author;
import com.mycompany.book.domain.model.Book;
import com.mycompany.book.service.author.AuthorDAService;
import com.mycompany.book.service.BookDAService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {
    @Autowired
    private BookDAService bookDAService;

    @Autowired
    private IEntityDAService<Author> authorDAService;

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> all() {
        return bookDAService.getAll();
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book add(@RequestBody Book author) {
        return bookDAService.save(author);
    }

    @GetMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findById(@PathVariable int id) {
        return bookDAService.findById(id);
    }


    @GetMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> allAuthors() throws IOException { return authorDAService.getAll(); }
}
