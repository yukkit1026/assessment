package org.example.assessment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookListDto {
    private Integer id;
    private String isbn_number;
    private String title;
    private String author;
}
