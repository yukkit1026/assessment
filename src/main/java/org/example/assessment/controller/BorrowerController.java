package org.example.assessment.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.assessment.model.dto.NewBorrowerRequstBody;
import org.example.assessment.model.dto.NewBorrowerResponseBody;
import org.example.assessment.model.dto.ReturnBookRequestBody;
import org.example.assessment.model.dto.StatusResponseBody;
import org.example.assessment.model.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/borrower")
@Slf4j
public class BorrowerController {
    @Autowired
    private LibraryService libraryService;

    @PostMapping("/register")
    public ResponseEntity<NewBorrowerResponseBody> registerNewBorrower(@RequestBody NewBorrowerRequstBody reqBody) {
        NewBorrowerResponseBody responseBody = new NewBorrowerResponseBody();
        try {
            responseBody.setBorrower_id(libraryService.registerNewBorrower(reqBody));
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage(), e);
            responseBody.setStatus(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/borrow/{id}")
    public ResponseEntity<StatusResponseBody> borrowBook(@PathVariable Integer id) {
        StatusResponseBody responseBody = new StatusResponseBody();
        try {
            libraryService.borrowBook(id);
            responseBody.setStatus("Successfully borrowed");
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (NullPointerException | IllegalArgumentException e) {
            log.warn(e.getMessage(), e);
            responseBody.setStatus(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        } catch
        (Exception e) {
            log.error(e.getMessage(), e);
            responseBody.setStatus("Something went wrong!!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }
    }

    @PostMapping("/return")
    public ResponseEntity<StatusResponseBody> returnBook(@RequestBody ReturnBookRequestBody reqBody) {
        StatusResponseBody responseBody = new StatusResponseBody();
        try {
            libraryService.returnBook(reqBody);
            responseBody.setStatus("Successfully returned");
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (NullPointerException | IllegalArgumentException e) {
            log.warn(e.getMessage(), e);
            responseBody.setStatus(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            responseBody.setStatus("Something went wrong!!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }
    }
}
