package org.example.assessment.model.dto;

import lombok.Data;

@Data
public class NewBookRequstBody {
    private String isbn_number;
    private String title;
    private String author;
}
