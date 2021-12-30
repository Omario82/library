package book.domain.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Author author;
    private String Title;
    private Date publicationDate;
}
