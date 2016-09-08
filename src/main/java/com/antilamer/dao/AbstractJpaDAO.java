package com.antilamer.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface AbstractJpaDAO<T extends Serializable> {
    public T findById(final Integer entityId);

    public T findById(final Long entityId);

    public T findByIdNoneCanceled(final Long entityId);

    public List<T> findAll();
    public List<T> findAll(String query);

    public void detach(final T entity);

    public void persist(final T entity);

    public void merge(final T entity);

    public T mergeWithReturn(final T entity);

    public void remove(final T entity);

    public boolean deleteById(final Long entityId);

    public void deleteById(final Integer entityId);

    public void persist(final List<T> entities);

    public List<T> findBySetIds(final Set<Long> ids, final Class<T> clazz);

}