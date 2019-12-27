package com.devcortes.hibernate;

import com.devcortes.hibernate.entity.Book;
import com.devcortes.hibernate.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> getAll() {
        List<Book> books = bookService.getAll();
        return new ResponseEntity<>(books, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> readById(@PathVariable Integer id) {
        Book book = bookService.readById(id);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<Book>> readByAuthorId(@PathVariable Integer id) {
        List<Book> books = bookService.readByAuthorId(id);
        return new ResponseEntity<>(books, HttpStatus.CREATED);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Book book) {
        bookService.save(book);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(Book book) {
        bookService.delete(book);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Book book) {
        bookService.update(book);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
