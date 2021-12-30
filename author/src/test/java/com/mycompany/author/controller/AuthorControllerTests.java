package com.mycompany.author.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.author.domain.model.Author;
import com.mycompany.author.service.AuthorDAService;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTests {
    private static final ObjectMapper om = new ObjectMapper();

    private MockMvc mvc;

    @Before
    public void setup() {
        List<Author> authors = setupAuthorsData();
        AuthorDAService authorDAService = new AuthorDAService(authors);
        this.mvc = MockMvcBuilders.standaloneSetup(new AuthorController(authorDAService)).build();
    }

    private List<Author> setupAuthorsData() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1,"J.D. Salinger","USA"));
        authors.add(new Author(2,"F. Scott. Fitzgerald","USA"));
        authors.add(new Author(3,"Jane Austen","UK"));
        authors.add(new Author(4,"Scott Hanselman","USA"));
        authors.add(new Author(5,"Jason N. Gaylord","USA"));
        authors.add(new Author(6,"Pranav Rastogi","India"));
        authors.add(new Author(7,"Todd Miranda","USA"));
        authors.add(new Author(8,"Christian Wenz","USA"));
        return authors;
    }

    @Test
    public void when_call_authors_get_all_authors() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/authors").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[" +
                                "{'id':1,'name':'J.D. Salinger','country':'USA'},"+
                                "{'id':2,'name':'F. Scott. Fitzgerald','country':'USA'},"+
                                "{'id':3,'name':'Jane Austen','country':'UK'},"+
                                "{'id':4,'name':'Scott Hanselman','country':'USA'},"+
                                "{'id':5,'name':'Jason N. Gaylord','country':'USA'},"+
                                "{'id':6,'name':'Pranav Rastogi','country':'India'},"+
                                "{'id':7,'name':'Todd Miranda','country':'USA'},"+
                                "{'id':8,'name':'Christian Wenz','country':'USA'}"+
                                "]"
                ));
    }

    @Test
    public void when_call_get_authors_by_id_get_author() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/authors/4").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':4,'name':'Scott Hanselman','country':'USA'}]"));
    }

    @Test
    public void when_call_post_authors_get_author_updated() throws Exception {
        Author newAuthor = Author.builder().name("Steven King").country("England").build();

        mvc.perform(MockMvcRequestBuilders.post("/authors").
                        content(om.writeValueAsString(newAuthor))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json("{'id':9,'name':'Steven King','country':'England'}"));
    }
}
