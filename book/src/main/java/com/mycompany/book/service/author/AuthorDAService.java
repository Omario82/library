package com.mycompany.book.service.author;

import com.mycompany.book.service.common.ApiConsumer;
import com.mycompany.domain.model.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorDAService extends ApiConsumer<Author, AuthorApiInfo> { }

