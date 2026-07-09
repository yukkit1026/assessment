package org.example.assessment.model.dto;

import lombok.Data;

@Data
public class ReturnBookRequestBody {
    private String isbn_number;
    private String author;
    private String title;
}
