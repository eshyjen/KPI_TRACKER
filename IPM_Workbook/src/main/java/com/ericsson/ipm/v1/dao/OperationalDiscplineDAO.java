package com.ericsson.ipm.v1.dao;

import java.util.List;

import com.ericsson.ipm.v1.domain.Operationaldiscpline;

/**
 * Interface for OperationaldiscplineDAO.
 * 
 * @author iqbal.hosain.khan@ericsson.com
 */

public interface OperationalDiscplineDAO {
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
	//public void save(Operationaldiscpline entity);

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
	public List<Operationaldiscpline> findByProperty(String propertyName,
			Object value);

	public List<Operationaldiscpline> findByNocomplianceq10(
			Object nocomplianceq10);

	public List<Operationaldiscpline> findByNocomplianceq11(
			Object nocomplianceq11);

	public List<Operationaldiscpline> findByNocomplianceq12(
			Object nocomplianceq12);

	public List<Operationaldiscpline> findByNocomplianceq13(
			Object nocomplianceq13);

	public List<Operationaldiscpline> findByNocomplianceq20(
			Object nocomplianceq20);

	public List<Operationaldiscpline> findByNocomplianceq21(
			Object nocomplianceq21);

	public List<Operationaldiscpline> findByNocomplianceq22(
			Object nocomplianceq22);

	public List<Operationaldiscpline> findByNocomplianceq23(
			Object nocomplianceq23);

	public List<Operationaldiscpline> findByNocomplianceq30(
			Object nocomplianceq30);

	public List<Operationaldiscpline> findByNocomplianceq31(
			Object nocomplianceq31);

	public List<Operationaldiscpline> findByNocomplianceq32(
			Object nocomplianceq32);

	public List<Operationaldiscpline> findByNocomplianceq33(
			Object nocomplianceq33);

	public List<Operationaldiscpline> findByNocomplianceq40(
			Object nocomplianceq40);

	public List<Operationaldiscpline> findByNocomplianceq41(
			Object nocomplianceq41);

	public List<Operationaldiscpline> findByNocomplianceq42(
			Object nocomplianceq42);

	public List<Operationaldiscpline> findByNocomplianceq43(
			Object nocomplianceq43);

	public List<Operationaldiscpline> findByCompetenceassessment10(
			Object competenceassessment10);

	public List<Operationaldiscpline> findByCompetenceassessment11(
			Object competenceassessment11);

	public List<Operationaldiscpline> findByCompetenceassessment12(
			Object competenceassessment12);

	public List<Operationaldiscpline> findByCompetenceassessment13(
			Object competenceassessment13);

	public List<Operationaldiscpline> findByMandatorytrainings10(
			Object mandatorytrainings10);

	public List<Operationaldiscpline> findByMandatorytrainings11(
			Object mandatorytrainings11);

	public List<Operationaldiscpline> findByMandatorytrainings12(
			Object mandatorytrainings12);

	public List<Operationaldiscpline> findByMandatorytrainings13(
			Object mandatorytrainings13);

	public List<Operationaldiscpline> findByCvupdates10(Object cvupdates10);

	public List<Operationaldiscpline> findByCvupdates11(Object cvupdates11);

	public List<Operationaldiscpline> findByCvupdates12(Object cvupdates12);

	public List<Operationaldiscpline> findByCvupdates13(Object cvupdates13);

	public List<Operationaldiscpline> findByTimeexpensebooking10(
			Object timeexpensebooking10);

	public List<Operationaldiscpline> findByTimeexpensebooking11(
			Object timeexpensebooking11);

	public List<Operationaldiscpline> findByTimeexpensebooking12(
			Object timeexpensebooking12);

	public List<Operationaldiscpline> findByTimeexpensebooking13(
			Object timeexpensebooking13);

	/**
	 * Find all Operationaldiscpline entities.
	 * 
	 * @return List<Operationaldiscpline> all Operationaldiscpline entities
	 */
	public List<Operationaldiscpline> findAll();
}