/**
 * 
 */
package com.ericsson.ipm.v1.dao;

import java.util.List;

/**
 * @author iqbal.hosain.khan@ericsson.com
 * 
 */
public interface GenericDAO<K, E> {
	
	/**
	 * Perform an initial save of a previously unsaved Userprofile entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * 
	 * @param entity
	 *            entity entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	
	E save(E entity);

	
	/**
	 * Perform an initial save of a previously unsaved Userprofile entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * 
	 * @param entity
	 *            entity entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	E update(E entity);

	
	/**
	 * Perform an initial save of a previously unsaved Userprofile entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * 
	 * @param entity
	 *            entity entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	void remove(E entity);

	
	/**
	 * Find all entity entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the entity property to query
	 * @param value
	 *            the property value to match
	 * @return List<entity> found by query
	 */
	
	E findById(K id);

	E getRefById(K id);

	List<E> findAll();
	
	List<E> findAll(boolean cacheable);
}
