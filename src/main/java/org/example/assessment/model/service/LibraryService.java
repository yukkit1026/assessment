package org.example.assessment.model.service;

import lombok.extern.slf4j.Slf4j;
import org.example.assessment.model.dto.*;
import org.example.assessment.model.entity.Book;
import org.example.assessment.model.entity.Borrower;
import org.example.assessment.model.repository.BookRepository;
import org.example.assessment.model.repository.BorrowerRepository;
import org.example.assessment.model.validator.GeneralValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
public class LibraryService {
    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BookRepository bookRepository;

    public BookListResponseBody getBookList() {
        BookListResponseBody responseBody = new BookListResponseBody();
        List<BookListDto> list = new ArrayList<>();
        List<Book> borrowerList = bookRepository.findAll();

        borrowerList.forEach(n -> {
            BookListDto bookListDto = BookListDto.builder()
                    .id(n.getId())
                    .isbn_number(n.getIsbnNumber())
                    .title(n.getTitle())
                    .author(n.getAuthor())
                    .build();
            list.add(bookListDto);
        });

        responseBody.setData(list);
        return responseBody;
    }

    public Integer registerNewBook(NewBookRequstBody newBookRequstBody) throws IllegalArgumentException {
        GeneralValidator.checkIsbnNumber(newBookRequstBody.getIsbn_number());
        List<Book> bookList = bookRepository.findAll().stream().filter(n -> n.getAuthor().equals(newBookRequstBody.getAuthor()) && n.getTitle().equals(newBookRequstBody.getAuthor()) && n.getIsbnNumber().equals(newBookRequstBody.getIsbn_number())).toList();

        if(!bookList.isEmpty())
            throw new IllegalArgumentException("Invalid book, already registered!!");

        Book book = new Book();

        book.setIsbnNumber(newBookRequstBody.getIsbn_number());
        book.setAuthor(newBookRequstBody.getAuthor());
        book.setTitle(newBookRequstBody.getTitle());

        bookRepository.save(book);

        return book.getId();
    }

    public Integer registerNewBorrower(NewBorrowerRequstBody newBorrowerRequstBody) throws IllegalArgumentException {
        GeneralValidator.checkEmail(newBorrowerRequstBody.getEmailAddress());

        Borrower borrower = new Borrower();
        borrower.setName(newBorrowerRequstBody.getName());
        borrower.setEmailAddress(newBorrowerRequstBody.getEmailAddress());

        borrowerRepository.save(borrower);

        return borrower.getId();
    }

    public void borrowBook(Integer bookId) throws NullPointerException, IllegalArgumentException {
        if (bookId == null) {
            throw new NullPointerException("Book ID cannot be null !!");
        }
        Optional<Book> book = Optional.of(bookRepository.findById(bookId).orElseThrow(() -> new NullPointerException("Book ID not found!!")));

        book.ifPresent(n -> {
            if (n.getDateBorrowed() != null && n.getDateReturned() == null) {
                throw new IllegalArgumentException("Book already borrowed and not yet returned !!");
            }

            n.setDateBorrowed(new Date());
            n.setDateReturned(null);
            bookRepository.save(n);
        });
    }

    public void returnBook(ReturnBookRequestBody reqBody) {

        List<Book> bookList = bookRepository.findAll().stream().filter(n -> n.getAuthor().equals(reqBody.getAuthor()) && n.getTitle().equals(reqBody.getAuthor()) && n.getIsbnNumber().equals(reqBody.getIsbn_number())).toList();

        if (bookList.isEmpty()) throw new NullPointerException("No book found");
        else if (bookList.size() > 1) throw new IllegalArgumentException("More than 1 book found");

        bookList.forEach(n->{
            n.setDateReturned(new Date());
            bookRepository.save(n);
        });
    }
}
