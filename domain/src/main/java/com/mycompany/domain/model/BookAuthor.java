package com.mycompany.domain.model;

import com.mycompany.domain.logic.IHaveId;
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
