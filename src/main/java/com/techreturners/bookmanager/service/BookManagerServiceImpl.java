package com.techreturners.bookmanager.service;

import com.techreturners.bookmanager.customException.BookAlreadyExistsException;
import com.techreturners.bookmanager.customException.BookNotFoundException;
import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.repository.BookManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookManagerServiceImpl implements BookManagerService {

    @Autowired
    BookManagerRepository bookManagerRepository;

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookManagerRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public Book insertBook(Book book) {
        if(book.getId() != null && bookManagerRepository.existsById(book.getId())) {
            throw new BookAlreadyExistsException("Book with ID " + book.getId() + " already exists");
        }
        return bookManagerRepository.save(book);

    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> book=bookManagerRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        }else {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }

    }

    //User Story 4 - Update Book By Id Solution
    @Override
    public void updateBookById(Long id, Book book) {
        Book retrievedBook = bookManagerRepository.findById(id).get();

        retrievedBook.setTitle(book.getTitle());
        retrievedBook.setDescription(book.getDescription());
        retrievedBook.setAuthor(book.getAuthor());
        retrievedBook.setGenre(book.getGenre());

        bookManagerRepository.save(retrievedBook);
    }

    @Override
    public String deleteBookById(Long id) {
        Optional<Book> bookOptional = bookManagerRepository.findById(id);
        if (bookOptional.isPresent()) {
            bookManagerRepository.deleteById(id);
            return "Book with ID " + id + " has been deleted";
        } else {
            throw new BookNotFoundException(id.toString());
        }

    }
}
