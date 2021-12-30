package com.mycompany.book.domain.model;

import com.mycompany.book.service.common.IHaveId;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthor implements IHaveId {
    private int id;
    private int authorId;
    private int bookId;
}
