package com.mycompany.author.controller;

import com.mycompany.author.domain.Author;
import com.mycompany.author.service.AuthorDAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    private AuthorDAService authorDAService;

    public AuthorController(AuthorDAService authorDAService){
        this.authorDAService = authorDAService;
    }

    public AuthorController(){ }

    @GetMapping("/authors")
    public List<Author> allAuthors() {
        return authorDAService.allAuthors();
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@RequestBody Author author) {
        return authorDAService.save(author);
    }

    @GetMapping("/authors/{id}")
    public List<Author> findById(@PathVariable int id) {
        return authorDAService.findById(id);
    }
}
