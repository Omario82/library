package com.mycompany.author.service;

import com.mycompany.author.domain.Author;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuthorDAService {
    private List<Author> authors;
    private static int MIN_AUTHOR_INDEX = 0;

    public AuthorDAService(List<Author> authors){
        if(this.authors == null)
            this.authors = new ArrayList<>();
        if(authors != null)
            this.authors.addAll(authors);
    }

    public List<Author> allAuthors() {
        return authors;
    }

    public Author save(Author author) {
        Optional<Author> authorMaxId = authors.stream().max(Comparator.comparing(Author::getId));
        int lastIndex = authorMaxId.map(Author::getId).orElseGet(() -> MIN_AUTHOR_INDEX);
        author.setId(++lastIndex);
        authors.add(author);
        return author;
    }

    public List<Author> findById(int id) {
        return authors.stream().filter(e -> e.getId() == id ).collect(Collectors.toList());
    }
}
