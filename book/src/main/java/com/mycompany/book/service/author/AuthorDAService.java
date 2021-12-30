package com.mycompany.book.service.author;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.book.domain.logic.IEntityDAService;
import com.mycompany.book.domain.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthorDAServiceBis extends ApiConsumer<Author, AuthorApi> {

    public AuthorDAServiceBis() {
        super(Author[].class);
    }
}

