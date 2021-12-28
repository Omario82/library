package com.mycompany.author.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Author {
    private int id;
    private String name;
    private String country;

    public Author(){}

    public Author(int id, String name, String country){
        this.id = id;
        this.name = name;
        this.country = country;
    }
}
