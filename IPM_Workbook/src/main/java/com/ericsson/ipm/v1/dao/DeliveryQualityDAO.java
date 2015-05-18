package com.ericsson.ipm.v1.dao;

import java.util.List;

import com.ericsson.ipm.v1.domain.DeliveryQuality;

public interface DeliveryQualityDAO {

	/**
	 * Perform an initial save of a previously unsaved Asset entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAssetDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Asset entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public DeliveryQuality save(DeliveryQuality entity);

	/**
	 * Delete a persistent Asset entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAssetDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Asset entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void remove(DeliveryQuality entity);

	/**
	 * Persist a previously saved Asset entity and return it or a copy of it to
	 * the sender. A copy of the Asset entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAssetDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Asset entity to update
	 * @return Asset the persisted Asset entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public DeliveryQuality update(DeliveryQuality entity);

	public DeliveryQuality findById(Integer id);
	
	public List<DeliveryQuality> findAll();

	
}
