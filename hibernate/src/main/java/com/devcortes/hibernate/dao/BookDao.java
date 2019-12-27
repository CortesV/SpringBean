package com.devcortes.hibernate.dao;

import com.devcortes.hibernate.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookDao implements IBook {

    private final SessionFactory sessionFactory;

    public BookDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<Book> getAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        List books = currentSession.createQuery("from Book ").list();
        return books;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Book readById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Book book = currentSession.get(Book.class, id);
        return book;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<Book> readByAuthorId(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        List<Book> books = (List<Book>) currentSession.createQuery("from Book b where b.authorId = :author_id").setParameter("author_id", id).list();
        return books;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void save(Book book) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.persist(book);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(Book book) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(book);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void update(Book book) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(book);
    }
}
