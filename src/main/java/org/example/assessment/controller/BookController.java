package org.example.assessment.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.assessment.model.dto.BookListResponseBody;
import org.example.assessment.model.dto.NewBookRequstBody;
import org.example.assessment.model.dto.NewBookResponseBody;
import org.example.assessment.model.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
@Slf4j
public class BookController {
    @Autowired
    private LibraryService libraryService;


    @PostMapping("/register")
    public ResponseEntity<NewBookResponseBody> registerNewBook(@RequestBody NewBookRequstBody reqBody) {
        NewBookResponseBody responseBody = new NewBookResponseBody();
        try {
            responseBody.setBook_id(libraryService.registerNewBook(reqBody));
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
        catch (IllegalArgumentException e){
            log.warn(e.getMessage(), e);
            responseBody.setStatus(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            responseBody.setStatus("Something went wrong");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<BookListResponseBody> getAllBookList() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(libraryService.getBookList());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }
    }
}
