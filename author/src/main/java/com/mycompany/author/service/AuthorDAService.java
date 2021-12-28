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
    private int MIN_AUTHOR_INDEX;

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
        MIN_AUTHOR_INDEX = 1;
        int lastIndex = authorMaxId.isPresent() ? authorMaxId.get().getId() : MIN_AUTHOR_INDEX;
        author.setId(++lastIndex);
        authors.add(author);
        return author;
    }

    public List<Author> findById(int id) {
        return authors.stream().filter(e -> e.getId() == id ).collect(Collectors.toList());
    }
}
