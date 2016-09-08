package com.antilamer.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Filter;
import org.hibernate.Session;

public abstract class AbstractJpaDAOImpl<T extends Serializable> {

    protected Class<T> clazz;
    @PersistenceContext(unitName = "ordersPortalDataPu")
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public AbstractJpaDAOImpl() {
        this.clazz = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]);
    }

    public T findById(final Integer id) {
        return entityManager.find(clazz, id);
    }

    public T findById(final Long id) {
        return entityManager.find(clazz, id);
    }

    public T findByIdNoneCanceled(final Long id) {
        String sql = "select t from " + clazz.getName() + " t where t.id = :id and t.canceled = :canceled ";
        Query query = entityManager.createQuery(sql, clazz);
        query.setParameter("id", id);
        query.setParameter("canceled", Boolean.FALSE);
        List<T> result = query.getResultList();
        if (result != null && !result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    public List<T> findAll(String query) {
        return entityManager.createQuery(query, clazz).getResultList();
    }

    public void detach(final T entity) {
        entityManager.detach(entity);
    }

    public void persist(final T entity) {
        entityManager.persist(entity);
    }

    public void persist(final List<T> entities) {
        if (entities != null && !entities.isEmpty()) {
            for (T t : entities) {
                entityManager.persist(t);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findBySetIds(final Set<Long> ids, final Class<T> clazz) {
        String sql = "select t from " + clazz.getName() + " t where t.id in (:ids)";
        Query query = entityManager.createQuery(sql, clazz);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    public void merge(final T entity) {
        entityManager.merge(entity);
    }

    public T mergeWithReturn(final T entity) {
        return entityManager.merge(entity);
    }

    public void remove(final T entity) {
        entityManager.remove(entity);
    }

    public boolean deleteById(final Long entityId) {
        final T entity = findById(entityId);
        remove(entity);
        if (entity != null) {
            remove(entity);
            return true;
        }
        return false;
    }

    public void deleteById(final Integer entityId) {
        final T entity = findById(entityId);
        remove(entity);
    }

    @SuppressWarnings("unchecked")
    public List<T> find(String sql, Object... params) {
        Query query = entityManager.createQuery(sql, clazz);
        List<Object> list = Arrays.asList(params);
        int idx = 1;
        if (list != null)
            for (Object obj : list) {
                query.setParameter(idx++, obj);
            }
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByParams(String sql, Map<String, Object> params) {
        Query query = entityManager.createQuery(sql);
        if (params != null) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        return query.getResultList();
    }

    public List<T> findRows(String sql, int maxRows, Object... params) {
        Query query = entityManager.createQuery(sql);
        List<Object> list = Arrays.asList(params);
        int idx = 1;
        if (list != null)
            for (Object obj : list) {
                query.setParameter(idx++, obj);
            }

        query.setMaxResults(maxRows);
        return query.getResultList();
    }

    public T findFirst(String sql, Object... params) {
        Query query = entityManager.createQuery(sql);
        List<Object> list = Arrays.asList(params);
        int idx = 1;
        for (Object obj : list) {
            query.setParameter(idx++, obj);
        }
        query.setMaxResults(1);
        List<T> result = query.getResultList();
        if (result != null && !result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public T forceFindFirst(String sql, Object... params) {
        Query query = entityManager.createQuery(sql);
        List<Object> list = Arrays.asList(params);
        int idx = 1;
        for (Object obj : list) {
            query.setParameter(idx++, obj);
        }
        query.setMaxResults(1);
        List<T> result = query.getResultList();
        if (result != null && !result.isEmpty()) {
            entityManager.refresh(result.get(0));
            return result.get(0);
        }
        return null;
    }

    public void forceReload() {
        entityManager.flush();
        entityManager.clear();
    }
}
