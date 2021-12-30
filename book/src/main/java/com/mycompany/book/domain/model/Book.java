package com.mycompany.book.domain.model;

import com.mycompany.book.service.common.IHaveId;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book implements IHaveId {

    private int id;
    private String title;
    private List<Author> authors;

    public Book(int id, String title){
        this.id = id;
        this.title = title;
        this.authors = new ArrayList<>();
    }
}

