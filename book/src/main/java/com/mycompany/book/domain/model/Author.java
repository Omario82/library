package com.mycompany.book.domain.model;

import com.mycompany.book.service.common.IHaveId;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author implements IHaveId {
    private int id;
    private String name;
    private String country;
    private List<Book> books;

    public Author(int id, String name, String country){
        this.id = id;
        this.name = name;
        this.country = country;
    }
}