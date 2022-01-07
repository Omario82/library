package com.mycompany.author;

import com.mycompany.domain.logic.IEntityDAService;
import com.mycompany.domain.model.Author;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class AuthorApplication implements CommandLineRunner {

    @Autowired
    private IEntityDAService<Author> authorDAService;

    public static void main(String[] args) {
        SpringApplication.run(AuthorApplication.class, args);
    }

    private void setupData() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1,"J.D. Salinger","USA"));
        authors.add(new Author(2,"F. Scott. Fitzgerald","USA"));
        authors.add(new Author(3,"Jane Austen","UK"));
        authors.add(new Author(4,"Scott Hanselman","USA"));
        authors.add(new Author(5,"Jason N. Gaylord","USA"));
        authors.add(new Author(6,"Pranav Rastogi","India"));
        authors.add(new Author(7,"Todd Miranda","USA"));
        authors.add(new Author(8,"Christian Wenz","USA"));

        authors.stream().map(e -> authorDAService.save(e)).collect(Collectors.toList());
        log.info(String.format("%d authors were loaded on AuthorDAService", authors.size()));
    }

    /**
     * in order to initialise services with data
     * @see <a href="https://stackoverflow.com/questions/29808051/spring-boot-annotation-autowired-of-service-fails">how to override a run in spring app</a>
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        setupData();
    }
}
