package com.footbalmanager.app.abstraction;

public interface Service<T> {
    T save(T entity);

    Iterable<T> save(Iterable<T> entities);

    T findOne(Long id);

    Iterable<T> findAll();

    void delete(Long id);

    void delete(T entity);


}
