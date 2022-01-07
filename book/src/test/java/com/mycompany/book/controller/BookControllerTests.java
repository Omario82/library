package com.mycompany.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.book.service.BookAuthorDAService;
import com.mycompany.book.service.BookDAService;
import com.mycompany.book.service.author.AuthorDAService;
import com.mycompany.domain.logic.IEntityDAService;
import com.mycompany.domain.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTests {
    private static final ObjectMapper om = new ObjectMapper();

    private MockMvc mvc;

    @Before
    public void setup() {

        BookDAService bookDAService = new BookDAService();
        IEntityDAService<Author> authorDAService = new AuthorDAService();
        IEntityDAService<BookAuthor> booksAuthorsDAService = new BookAuthorDAService();
        List<Book> books = new ArrayList<>();
        List<BookAuthor> booksAuthors = new ArrayList<>();
        setupData(books, booksAuthors, bookDAService, booksAuthorsDAService);

        this.mvc = MockMvcBuilders.standaloneSetup(new BookController(bookDAService, authorDAService)).build();
    }

    private void setupData(List<Book> books, List<BookAuthor> booksAuthors, BookDAService bookDAService, IEntityDAService<BookAuthor> booksAuthorsDAService) {
        books.add(new Book(1,"The Catcher in the Rye"));
        books.add(new Book(2,"Nine Stories"));
        books.add(new Book(3,"Franny and Zooey"));
        books.add(new Book(4,"The Great Gatsby"));
        books.add(new Book(5,"Tender id the Night"));
        books.add(new Book(6,"Pride and Prejudice"));
        books.add(new Book(7,"Professional ASP.NET 4.5 in C# and VB"));

        books.stream().map(bookDAService::save).collect(Collectors.toList());

        booksAuthors.add(new BookAuthor(1,1,1));
        booksAuthors.add(new BookAuthor(2,2,1));
        booksAuthors.add(new BookAuthor(3,3,1));
        booksAuthors.add(new BookAuthor(4,4,2));
        booksAuthors.add(new BookAuthor(5,5,2));
        booksAuthors.add(new BookAuthor(6,6,3));
        booksAuthors.add(new BookAuthor(7,7,4));
        booksAuthors.add(new BookAuthor(8,7,5));
        booksAuthors.add(new BookAuthor(9,7,6));
        booksAuthors.add(new BookAuthor(10,7,7));
        booksAuthors.add(new BookAuthor(11,7,8));

        booksAuthors.stream().map(booksAuthorsDAService::save).collect(Collectors.toList());
    }

    @Test
    public void when_call_books_get_all_books() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[" +
                                "{'id':1,'title':'The Catcher in the Rye','authors':[]},"+
                                "{'id':2,'title':'Nine Stories','authors':[]},"+
                                "{'id':3,'title':'Franny and Zooey','authors':[]},"+
                                "{'id':4,'title':'The Great Gatsby','authors':[]},"+
                                "{'id':5,'title':'Tender id the Night','authors':[]},"+
                                "{'id':6,'title':'Pride and Prejudice','authors':[]},"+
                                "{'id':7,'title':'Professional ASP.NET 4.5 in C# and VB','authors':[]}"+
                                "]"
                ));
    }

    @Test
    public void when_call_get_books_by_id_get_book() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/4").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':4,'title':'The Great Gatsby','authors':[]}"));
    }

    @Test
    public void when_call_post_books_get_book_updated() throws Exception {
        Book newBook = Book.builder().title("Star Wars").build();

        mvc.perform(MockMvcRequestBuilders.post("/books").
                        content(om.writeValueAsString(newBook))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json("{'id':8,'title':'Star Wars'}"));
    }
}
