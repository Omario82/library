package book.service;

import book.domain.logic.IEntityDAService;
import book.domain.model.Book;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class BookDAService implements IEntityDAService<Book> {
    private List<Book> books;
    private static int MIN_AUTHOR_INDEX = 0;

    public BookDAService(List<Book> authors) {
        if (this.books == null)
            this.books = new ArrayList<>();
        if (authors != null)
            this.books.addAll(authors);
    }

    @Override
    public List<Book> getAll() {
        throw new NotImplementedException();
    }

    public Book save(Book book) {
        throw new NotImplementedException();
    }

    public List<Book> findById(int id) {
        throw new NotImplementedException();
    }
}
