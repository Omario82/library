package com.mycompany.book.service.author;

import com.mycompany.book.domain.model.Author;
import com.mycompany.book.service.common.ApiConsumer;
import org.springframework.stereotype.Component;

@Component
public class AuthorDAService extends ApiConsumer<Author, AuthorApiInfo> {

    public AuthorDAService() {
        super(Author[].class);
    }
}

