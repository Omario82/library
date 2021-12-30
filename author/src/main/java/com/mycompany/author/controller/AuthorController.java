package com.mycompany.author.controller;

import com.mycompany.author.domain.logic.IEntityDAService;
import com.mycompany.author.domain.model.Author;
import com.mycompany.author.service.AuthorDAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    private IEntityDAService<Author> authorDAService;

    public AuthorController(IEntityDAService<Author> authorDAService){
        this.authorDAService = authorDAService;
    }

    public AuthorController(){ }

    @GetMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> all() {
        return authorDAService.getAll();
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author add(@RequestBody Author author) {
        return authorDAService.save(author);
    }

    @GetMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> findById(@PathVariable int id) {
        return authorDAService.findById(id);
    }
}
