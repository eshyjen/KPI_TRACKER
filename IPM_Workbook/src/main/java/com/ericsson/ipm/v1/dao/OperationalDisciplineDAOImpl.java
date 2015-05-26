package com.ericsson.ipm.v1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.ipm.v1.domain.OperationalDiscipline;

/**
 * A data access object (DAO) providing persistence and search support for
 * Operationaldiscpline entities. Transaction control of the save(), update()
 * and delete() operations must be handled externally by senders of these
 * methods or must be manually added to each of these methods for data to be
 * persisted to the JPA datastore.
 *
 * @see com.ericsson.ipm.v1.domain.OperationalDiscipline
 * @author iqbal.hosain.khan@ericsson.com
 */
@Repository("operationalDiscplineDAO")
@Transactional
public class OperationalDisciplineDAOImpl extends BaseDAO<Integer, OperationalDiscipline> implements OperationalDisciplineDAO {
	// property constants
	public static final String OPERATIONALDISCIPLINENAME = "operationalDisciplineName";
	public static final String FREQUENCY = "frequency";
	public static final String QUARTER = "quarter";
	public static final String NONCOMPLIANCE = "nonCompliance";



	private static final Logger LOGGER = LoggerFactory.getLogger(OperationalDisciplineDAOImpl.class);



	private EntityManager getEntityManager() {
		return entityManager;
	}

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
	 * OperationaldiscplineDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            Operationaldiscpline entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public OperationalDiscipline save(OperationalDiscipline entity) {
		LOGGER.info("saving Operationaldiscpline instance");
		try {
			getEntityManager().persist(entity);
			LOGGER.info("save successful");
		} catch (RuntimeException re) {
			LOGGER.info("save failed"+ re);
			throw re;
		}
		return entity;
	}

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
	 * OperationaldiscplineDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 *
	 * @param entity
	 *            Operationaldiscpline entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	/*public void remove(Operationaldiscpline entity) {
		LOGGER.info("deleting Operationaldiscpline instance");
		try {
			entity = getEntityManager().getReference(
					Operationaldiscpline.class, entity.getId());
			getEntityManager().remove(entity);
			LOGGER.info("delete successful");
		} catch (RuntimeException re) {
			LOGGER.info("delete failed"+ re);
			throw re;
		}
	}*/

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
	 * entity = OperationaldiscplineDAO.update(entity);
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
	/*public Operationaldiscpline update(Operationaldiscpline entity) {
		LOGGER.info("updating Operationaldiscpline instance");
		try {
			Operationaldiscpline result = getEntityManager().merge(entity);
			LOGGER.info("update successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.info("update failed"+ re);
			throw re;
		}
	}*/

	/*public Operationaldiscpline findById(Integer id) {
		LOGGER.info(
				"finding Operationaldiscpline instance with id: " + id);
		try {
			Operationaldiscpline instance = getEntityManager().find(
					Operationaldiscpline.class, id);
			return instance;
		} catch (RuntimeException re) {
			LOGGER.info("find failed"+ re);
			throw re;
		}
	}
*/
	/**
	 * Find all Operationaldiscpline entities with a specific property value.
	 *
	 * @param propertyName
	 *            the name of the Operationaldiscpline property to query
	 * @param value
	 *            the property value to match
	 * @return List<Operationaldiscpline> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<OperationalDiscipline> findByProperty(String propertyName,
			final Object value) {
		LOGGER.info(
				"finding Operationaldiscpline instance with property: "
						+ propertyName + ", value: " + value);
		try {
			final String queryString = "select model from Operationaldiscpline model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LOGGER.info("find by property name failed"+ re);
			throw re;
		}
	}

	public List<OperationalDiscipline> findByOperationalDisciplineName(
			Object operationalDisciplineName) {
		return findByProperty(OPERATIONALDISCIPLINENAME, operationalDisciplineName);
	}

	public List<OperationalDiscipline> findByFrequency(
			Object frequency) {
		return findByProperty(FREQUENCY, frequency);
	}

	public List<OperationalDiscipline> findByQuarter(
			Object quarter) {
		return findByProperty(QUARTER, quarter);
	}

	public List<OperationalDiscipline> findByNonCompliance(
			Object nonCompliance) {
		return findByProperty(NONCOMPLIANCE, nonCompliance);
	}

	/**
	 * Find all Operationaldiscpline entities.
	 *
	 * @return List<Operationaldiscpline> all Operationaldiscpline entities
	 */
	@SuppressWarnings("unchecked")
	public List<OperationalDiscipline> findAll() {
		LOGGER.info("finding all Operationaldiscpline instances");
		try {
			final String queryString = "select model from Operationaldiscpline model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LOGGER.info("find all failed"+ re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.ericsson.ipm.v1.dao.OperationalDisciplineDAO#getOperationalDisciplineDetail(java.lang.String)
	 */
	@Override
	public OperationalDiscipline getOperationalDisciplineDetail(String id) {
		// TODO Auto-generated method stub
		LOGGER.info(
				"finding Operationaldiscpline instance with id: "+ id);
		try {
			final String queryString = "select model from OperationalDiscipline model where model.id= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", id);
			return (OperationalDiscipline) query.getSingleResult();
		} catch (RuntimeException re) {
			LOGGER.info("find by property name failed"+ re);
			throw re;
		}
	}

}