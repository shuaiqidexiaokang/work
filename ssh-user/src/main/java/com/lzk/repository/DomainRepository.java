package com.lzk.repository;

import java.io.Serializable;
import java.util.List;

public interface DomainRepository<T,PK extends Serializable>{
    T load(PK id);

    T get(PK id);

    List<T> findAll();

    PK save(T entity);

    void delete(PK id);

    void saveOrUpdate(T entity);

    void persist(T entity);

    void flush();
}