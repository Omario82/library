package com.mycompany.book.service;

import com.mycompany.book.domain.logic.IEntityDAService;
import com.mycompany.book.domain.model.Author;
import com.mycompany.book.domain.model.Book;
import com.mycompany.book.domain.model.BookAuthor;
import com.mycompany.book.service.common.EntityDAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookDAService extends EntityDAService<Book> {
    @Autowired
    private IEntityDAService<BookAuthor> booksAuthorsDAService;
    @Autowired
    private IEntityDAService<Author> authorsDAService;

    public Book getWithDependencies(int bookId) throws IOException {
        Book resutl = getById(bookId);
        getAuthorIdsForBook(bookId).stream()
                .map(e -> resutl.getAuthors().add(authorsDAService.getById(e)))
                .collect(Collectors.toList());
        return resutl;
    }

    // helpers
    private List<Integer> getAuthorIdsForBook(int bookId) throws IOException {
        return booksAuthorsDAService.getAll().stream()
                .filter(e -> e.getBookId() == bookId)
                .map(e -> e.getAuthorId())
                .collect(Collectors.toList());
    }

}
