# 📖 Minimalist Book Manager API

## Introduction
This is the starter repository for the Further APIs session. It provides a start to creating a Minimalist Book Manager API
using a Test-Driven Development approach.

### Pre-Requisites
- Java SE Development Kit 17
- Maven

### Technologies & Dependencies
- Spring Boot
- Spring Web
- H2 Database
- Lombok
- Spring Data JPA

### How to Get Started
- Fork this repo to your Github and then clone the forked version of this repo

### Main Entry Point
- The Main Entry Point for the application is: [BookmanagerApplication.java](src/main/java/com/techreturners/bookmanager/BookmanagerApplication.java)

### Running the Unit Tests
- You can run the unit tests in IntelliJ, or you can go to your terminal and inside the root of this directory, run:

`mvn test`

### Tasks

Here are some tasks for you to work on:

📘 Discussion Task

Explore the code and make notes on the following features and how it is being implemented in the code. We'd like you to note down what classes and methods are used and how the objects interact.

The features are:
- Get All Books
- Get a Book by ID
- Add a Book
- Update a Book

📘 Task 1: Implement the following User Story with tests.

`User Story: As a user, I want to use the Book Manager API to delete a book using its ID`


📘 Extension Task: Oh no! 😭 We've only covered the happy paths in the solution, can you figure out a way
to add in exception handling to the project? 

- Clue 1: What if someone wants to add a book with an ID for a book that already exists? How do we handle this gracefully?


- Clue 2: What if someone wants to find a book by an ID that doesn't yet exist? 
  How can we improve the API by handling errors gracefully and show a helpful message to the client?

Task Steps:

As a user, I want to use the Book Manager API to delete a book using its ID.

1. Implement an API endpoint that accepts a DELETE request and the ID of the book to be deleted.

2. Create a method deleteById to perform delete operation and create test method in testclass.

3. Check if the book with the given ID exists in the system. If it does, delete the book and return a success message to the user.

4. If the book with the given ID doesn't exist, return an error message to the user stating that the book was not found.

The features added:

 - Delete a Book by Id

Then created a CustomException to handle the exception for BookAlreadyExistsException and BookNotFoundException.

- In the case of adding a Book with an ID for a book that already exists,raise an exception.The BookAlreadyExistsException handles the exception and give the
error message that informs the user that the book already exists in the system.

- In the case of finding a book by an ID that doesn't yet exist,raise an exception.The BookNotFoundException handles the exception and give the error message that informs the user that the book was not found in the system or database.