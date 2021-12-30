package com.mycompany.book.service;

import com.mycompany.book.domain.model.BookAuthor;
import com.mycompany.book.service.common.EntityDAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BookAuthorDAService extends EntityDAService<BookAuthor> {

    public BookAuthorDAService(List<BookAuthor> booksAuthors) {
        super(booksAuthors);
    }

}
