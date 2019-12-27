package com.devcortes.hibernate.dao;

import com.devcortes.hibernate.entity.Book;

import java.util.List;

public interface IBook {

    List<Book> getAll();
    Book readById(Integer id);
    List<Book> readByAuthorId(Integer id);
    void save(Book book);
    void delete(Book book);
    void update(Book book);
}
