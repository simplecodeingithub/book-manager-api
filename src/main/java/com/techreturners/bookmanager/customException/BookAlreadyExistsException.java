package com.techreturners.bookmanager.customException;

public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(String bookId){
        super("Book with Id" + bookId + "already exists in the Database");
    }
}
