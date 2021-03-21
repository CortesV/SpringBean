package com.devcortes.hibernate.services;

import com.devcortes.hibernate.dao.IBook;
import com.devcortes.hibernate.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private IBook iBook;

    public BookService(IBook iBook) {
        this.iBook = iBook;
    }

    public List<Book> getAll() {
        List<Book> books = iBook.getAll();
        return books;
    }

    public Book readById(Integer id) {
        Book book = iBook.readById(id);
        return book;
    }

    public List<Book> readByAuthorId(Integer id) {
        List<Book> books = iBook.readByAuthorId(id);
        return books;
    }

    public void save(Book book) {
        iBook.save(book);
    }

    public void delete(Book book) {
        iBook.delete(book);
    }

    public void update(Book book) {
        iBook.update(book);
    }
}
