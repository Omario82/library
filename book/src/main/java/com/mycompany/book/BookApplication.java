package com.mycompany.book;

import com.mycompany.book.domain.logic.IEntityDAService;
import com.mycompany.book.domain.model.Book;
import com.mycompany.book.domain.model.BookAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class BookApplication implements CommandLineRunner {

    @Autowired
    private IEntityDAService<Book> bookDAService;
    @Autowired
    private IEntityDAService<BookAuthor> booksAuthorsDAService;

    public static void main(String[] args) { SpringApplication.run(BookApplication.class, args);    }

    private void setupData() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1,"The Catcher in the Rye"));
        books.add(new Book(2,"Nine Stories"));
        books.add(new Book(3,"Franny and Zooey"));
        books.add(new Book(4,"The Great Gatsby"));
        books.add(new Book(5,"Tender id the Night"));
        books.add(new Book(6,"Pride and Prejudice"));
        books.add(new Book(7,"Professional ASP.NET 4.5 in C# and VB"));

        books.stream().map(bookDAService::save).collect(Collectors.toList());

        List<BookAuthor> booksAuthors = new ArrayList<>();
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
