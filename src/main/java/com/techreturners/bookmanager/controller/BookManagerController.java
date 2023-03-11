package com.techreturners.bookmanager.controller;

import com.techreturners.bookmanager.customException.BookAlreadyExistsException;
import com.techreturners.bookmanager.customException.BookNotFoundException;
import com.techreturners.bookmanager.customException.CustomErrorResponse;
import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.service.BookManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookManagerController {

    @Autowired
    BookManagerService bookManagerService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookManagerService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping({"/{bookId}"})
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        try{
            Book book =bookManagerService.getBookById(bookId);
            return new ResponseEntity<>(book,HttpStatus.OK);
        }catch (BookNotFoundException e){
            //CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.NOT_FOUND, "Book not found with ID " + bookId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity<>(bookManagerService.getBookById(bookId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        try {
            //System.out.println("Attempting to add book with ID " + book.getId());
            Book newBook = bookManagerService.insertBook(book);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("book", "/api/v1/book/" + newBook.getId().toString());
            System.out.println("Book added successfully with ID " + newBook.getId());
            return new ResponseEntity<>(newBook, httpHeaders, HttpStatus.CREATED);
        } catch (BookAlreadyExistsException e) {
            System.out.println("Book with ID " + book.getId() + " already exists in the database");
            String errorMessage = "Book with ID " + book.getId() + " already exists in the database";
            CustomErrorResponse errorResponse=new CustomErrorResponse(errorMessage) ;
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            System.out.println("Error occurred while adding book with ID " + book.getId());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //User Story 4 - Update Book By Id Solution
    @PutMapping({"/{bookId}"})
    public ResponseEntity<Book> updateBookById(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        bookManagerService.updateBookById(bookId, book);
        return new ResponseEntity<>(bookManagerService.getBookById(bookId), HttpStatus.OK);
    }
    @DeleteMapping({"/{bookId}"})
    public ResponseEntity<String> deleteBookById(@PathVariable Long bookId){
        String message= bookManagerService.deleteBookById(bookId);
        return ResponseEntity.ok(message);

    }

    // Exception handler for BookAlreadyExistsException
   @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<String> handleBookAlreadyExistsException(BookAlreadyExistsException e){
        return  new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }
}
