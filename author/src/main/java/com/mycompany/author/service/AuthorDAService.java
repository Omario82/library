package com.mycompany.author.service;

import com.mycompany.author.domain.logic.IEntityDAService;
import com.mycompany.author.domain.model.Author;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AuthorDAService implements IEntityDAService<Author> {
    private List<Author> authors;
    private static int MIN_AUTHOR_INDEX = 0;

    public AuthorDAService(List<Author> authors){
        if(this.authors == null)
            this.authors = new ArrayList<>();
        if(authors != null){
            this.authors.addAll(authors);
            // for more details see https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html
            log.info(String.format("%d authors were loaded on AuthorDAService", authors.size()));
        }
    }

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

    public List<Author> findById(int id) {
        return authors.stream().filter(e -> e.getId() == id ).collect(Collectors.toList());
    }
}
