package org.example.assessment.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewBookResponseBody extends StatusResponseBody {
    private Integer book_id;
}
