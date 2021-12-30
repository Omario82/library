package com.mycompany.book.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private int id;
    private String name;
    private String country;
}