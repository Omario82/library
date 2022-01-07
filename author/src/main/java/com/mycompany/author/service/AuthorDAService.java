package com.mycompany.author.service;

import com.mycompany.domain.logic.IEntityDAService;
import com.mycompany.domain.model.Author;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorDAService implements IEntityDAService<Author> {
    private List<Author> authors = new ArrayList<>();;
    private static int MIN_AUTHOR_INDEX = 0;

    public List<Author> getAll() {
        return authors;
    }

    public Author save(Author item) {
        Optional<Author> authorMaxId = authors.stream().max(Comparator.comparing(Author::getId));
        int lastIndex = authorMaxId.map(Author::getId).orElseGet(() -> MIN_AUTHOR_INDEX);
        item.setId(++lastIndex);
        authors.add(item);
        return item;
    }

    public Author getById(int id) {
        List<Author> results = authors.stream().filter(e -> e.getId() == id ).collect(Collectors.toList());
        return (results != null && results.size() == 1) ? results.get(0) : null;
    }
}
