/**
 * 
 */
package com.ericsson.ipm.v1.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.ericsson.v1.util.Constants;

/**
 * @author iqbal.hosain.khan@ericsson.com
 * 
 */
public abstract class BaseDAO<K, E> implements GenericDAO<K, E> {
	protected Class<E> entityClass;

	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings(Constants.UNCHECKED_COMPILER_WARNING)
	public BaseDAO() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass
				.getActualTypeArguments()[1];
	}

	@Transactional
	public E save(E entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Transactional
	public E update(E entity) {
		return entityManager.merge(entity);
	}

	@Transactional
	public void remove(E entity) {
		entityManager.remove(entity);
	}

	public E findById(K id) {
		return entityManager.find(entityClass, id);
	}

	public E getRefById(K id) {
		return entityManager.getReference(entityClass, id);
	}

	public List<E> findAll() {
		return findAll(false);
	}

	private String getEntityClassNm() {
		return entityClass.getName();
	}

	public E findByUniqueKey(String key, Object value) {
		TypedQuery<E> query = entityManager.createQuery("FROM "
				+ getEntityClassNm() + " WHERE " + key + " = :value",
				entityClass);
		query.setParameter("value", value);
		return findUniqueEntityIfExists(query);
	}

	@SuppressWarnings(Constants.UNCHECKED_COMPILER_WARNING)
	protected E findUniqueEntityIfExists(Query query) {
		query.setFirstResult(0);
		query.setMaxResults(1);
		List<E> result = query.getResultList();
		return (result.size() == 0) ? null : result.get(0);
	}

	@SuppressWarnings(Constants.UNCHECKED_COMPILER_WARNING)
	protected List<E> findEntityListIfExists(Query query) {
		return query.getResultList();
	}

	// for master-data lookup - pass true
	@Override
	public List<E> findAll(boolean cacheable) {
		TypedQuery<E> query = entityManager.createQuery("from "
				+ getEntityClassNm(), entityClass);
		if (cacheable)
			query.setHint("org.hibernate.cacheable", "true");
		return query.getResultList();
	}

}
