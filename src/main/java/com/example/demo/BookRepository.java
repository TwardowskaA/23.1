package com.example.demo;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookRepository {

    private EntityManager entityManager;

    public BookRepository(EntityManagerFactory entityManagerFactory){
        entityManager=entityManagerFactory.createEntityManager();
    }

    public List<Book> findAll(){
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

    public Book findById(Long id) {
        Book book = entityManager.find(Book.class, id);
        return book;
    }

    public void update(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

    public void remove(Long id){
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

}
