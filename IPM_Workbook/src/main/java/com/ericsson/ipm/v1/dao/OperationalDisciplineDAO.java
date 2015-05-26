package com.ericsson.ipm.v1.dao;

import java.util.List;

import com.ericsson.ipm.v1.domain.OperationalDiscipline;

/**
 * Interface for OperationaldiscplineDAO.
 *
 * @author iqbal.hosain.khan@ericsson.com
 */

public interface OperationalDisciplineDAO {
	/**
	 * Perform an initial save of a previously unsaved Operationaldiscpline
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IOperationaldiscplineDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            Operationaldiscpline entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public OperationalDiscipline save(OperationalDiscipline entity);

	/**
	 * Delete a persistent Operationaldiscpline entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IOperationaldiscplineDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 *
	 * @param entity
	 *            Operationaldiscpline entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	//public void delete(Operationaldiscpline entity);

	/**
	 * Persist a previously saved Operationaldiscpline entity and return it or a
	 * copy of it to the sender. A copy of the Operationaldiscpline entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IOperationaldiscplineDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            Operationaldiscpline entity to update
	 * @return Operationaldiscpline the persisted Operationaldiscpline entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	//public Operationaldiscpline update(Operationaldiscpline entity);

	//public Operationaldiscpline findById(Integer id);

	/**
	 * Find all Operationaldiscpline entities with a specific property value.
	 *
	 * @param propertyName
	 *            the name of the Operationaldiscpline property to query
	 * @param value
	 *            the property value to match
	 * @return List<Operationaldiscpline> found by query
	 */
	public List<OperationalDiscipline> findByProperty(String propertyName,
			Object value);

	public List<OperationalDiscipline> findByOperationalDisciplineName(
			Object operationalDisciplineName);

	public List<OperationalDiscipline> findByFrequency(
			Object frequency);

	public List<OperationalDiscipline> findByQuarter(
			Object quarter);

	public List<OperationalDiscipline> findByNonCompliance(
			Object nonCompliance);

	public OperationalDiscipline getOperationalDisciplineDetail(String id);

	/**
	 * Find all Operationaldiscpline entities.
	 *
	 * @return List<Operationaldiscpline> all Operationaldiscpline entities
	 */
	public List<OperationalDiscipline> findAll();
}