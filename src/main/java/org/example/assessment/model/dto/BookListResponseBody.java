package org.example.assessment.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookListResponseBody {
    private List<BookListDto> data;
}
