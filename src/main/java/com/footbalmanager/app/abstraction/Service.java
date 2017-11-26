package com.footbalmanager.app.abstraction;

public interface Service<T> {
    T save(T entity);

    T findOne(Long id);

    Iterable<T> findAll();

    void delete(Long id);

    void delete(T entity);


}
