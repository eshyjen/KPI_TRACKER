package com.ericsson.ipm.v1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.ipm.v1.domain.Operationaldiscpline;

/**
 * A data access object (DAO) providing persistence and search support for
 * Operationaldiscpline entities. Transaction control of the save(), update()
 * and delete() operations must be handled externally by senders of these
 * methods or must be manually added to each of these methods for data to be
 * persisted to the JPA datastore.
 * 
 * @see com.ericsson.ipm.v1.domain.Operationaldiscpline
 * @author iqbal.hosain.khan@ericsson.com
 */
@Repository("operationalDiscplineDAO")
@Transactional
public class OperationalDiscplineDAOImpl extends BaseDAO<Integer, Operationaldiscpline> implements OperationalDiscplineDAO {
	// property constants
	public static final String NOCOMPLIANCEQ10 = "nocomplianceq10";
	public static final String NOCOMPLIANCEQ11 = "nocomplianceq11";
	public static final String NOCOMPLIANCEQ12 = "nocomplianceq12";
	public static final String NOCOMPLIANCEQ13 = "nocomplianceq13";
	public static final String NOCOMPLIANCEQ20 = "nocomplianceq20";
	public static final String NOCOMPLIANCEQ21 = "nocomplianceq21";
	public static final String NOCOMPLIANCEQ22 = "nocomplianceq22";
	public static final String NOCOMPLIANCEQ23 = "nocomplianceq23";
	public static final String NOCOMPLIANCEQ30 = "nocomplianceq30";
	public static final String NOCOMPLIANCEQ31 = "nocomplianceq31";
	public static final String NOCOMPLIANCEQ32 = "nocomplianceq32";
	public static final String NOCOMPLIANCEQ33 = "nocomplianceq33";
	public static final String NOCOMPLIANCEQ40 = "nocomplianceq40";
	public static final String NOCOMPLIANCEQ41 = "nocomplianceq41";
	public static final String NOCOMPLIANCEQ42 = "nocomplianceq42";
	public static final String NOCOMPLIANCEQ43 = "nocomplianceq43";
	public static final String COMPETENCEASSESSMENT10 = "competenceassessment10";
	public static final String COMPETENCEASSESSMENT11 = "competenceassessment11";
	public static final String COMPETENCEASSESSMENT12 = "competenceassessment12";
	public static final String COMPETENCEASSESSMENT13 = "competenceassessment13";
	public static final String MANDATORYTRAININGS10 = "mandatorytrainings10";
	public static final String MANDATORYTRAININGS11 = "mandatorytrainings11";
	public static final String MANDATORYTRAININGS12 = "mandatorytrainings12";
	public static final String MANDATORYTRAININGS13 = "mandatorytrainings13";
	public static final String CVUPDATES10 = "cvupdates10";
	public static final String CVUPDATES11 = "cvupdates11";
	public static final String CVUPDATES12 = "cvupdates12";
	public static final String CVUPDATES13 = "cvupdates13";
	public static final String TIMEEXPENSEBOOKING10 = "timeexpensebooking10";
	public static final String TIMEEXPENSEBOOKING11 = "timeexpensebooking11";
	public static final String TIMEEXPENSEBOOKING12 = "timeexpensebooking12";
	public static final String TIMEEXPENSEBOOKING13 = "timeexpensebooking13";

	
	private static final Logger LOGGER = LoggerFactory.getLogger(OperationalDiscplineDAOImpl.class);

	
	
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
	/*public Operationaldiscpline save(Operationaldiscpline entity) {
		LOGGER.info("saving Operationaldiscpline instance");
		try {
			getEntityManager().persist(entity);
			LOGGER.info("save successful");
		} catch (RuntimeException re) {
			LOGGER.info("save failed"+ re);
			throw re;
		}
		return entity;
	}*/

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
	public List<Operationaldiscpline> findByProperty(String propertyName,
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

	public List<Operationaldiscpline> findByNocomplianceq10(
			Object nocomplianceq10) {
		return findByProperty(NOCOMPLIANCEQ10, nocomplianceq10);
	}

	public List<Operationaldiscpline> findByNocomplianceq11(
			Object nocomplianceq11) {
		return findByProperty(NOCOMPLIANCEQ11, nocomplianceq11);
	}

	public List<Operationaldiscpline> findByNocomplianceq12(
			Object nocomplianceq12) {
		return findByProperty(NOCOMPLIANCEQ12, nocomplianceq12);
	}

	public List<Operationaldiscpline> findByNocomplianceq13(
			Object nocomplianceq13) {
		return findByProperty(NOCOMPLIANCEQ13, nocomplianceq13);
	}

	public List<Operationaldiscpline> findByNocomplianceq20(
			Object nocomplianceq20) {
		return findByProperty(NOCOMPLIANCEQ20, nocomplianceq20);
	}

	public List<Operationaldiscpline> findByNocomplianceq21(
			Object nocomplianceq21) {
		return findByProperty(NOCOMPLIANCEQ21, nocomplianceq21);
	}

	public List<Operationaldiscpline> findByNocomplianceq22(
			Object nocomplianceq22) {
		return findByProperty(NOCOMPLIANCEQ22, nocomplianceq22);
	}

	public List<Operationaldiscpline> findByNocomplianceq23(
			Object nocomplianceq23) {
		return findByProperty(NOCOMPLIANCEQ23, nocomplianceq23);
	}

	public List<Operationaldiscpline> findByNocomplianceq30(
			Object nocomplianceq30) {
		return findByProperty(NOCOMPLIANCEQ30, nocomplianceq30);
	}

	public List<Operationaldiscpline> findByNocomplianceq31(
			Object nocomplianceq31) {
		return findByProperty(NOCOMPLIANCEQ31, nocomplianceq31);
	}

	public List<Operationaldiscpline> findByNocomplianceq32(
			Object nocomplianceq32) {
		return findByProperty(NOCOMPLIANCEQ32, nocomplianceq32);
	}

	public List<Operationaldiscpline> findByNocomplianceq33(
			Object nocomplianceq33) {
		return findByProperty(NOCOMPLIANCEQ33, nocomplianceq33);
	}

	public List<Operationaldiscpline> findByNocomplianceq40(
			Object nocomplianceq40) {
		return findByProperty(NOCOMPLIANCEQ40, nocomplianceq40);
	}

	public List<Operationaldiscpline> findByNocomplianceq41(
			Object nocomplianceq41) {
		return findByProperty(NOCOMPLIANCEQ41, nocomplianceq41);
	}

	public List<Operationaldiscpline> findByNocomplianceq42(
			Object nocomplianceq42) {
		return findByProperty(NOCOMPLIANCEQ42, nocomplianceq42);
	}

	public List<Operationaldiscpline> findByNocomplianceq43(
			Object nocomplianceq43) {
		return findByProperty(NOCOMPLIANCEQ43, nocomplianceq43);
	}

	public List<Operationaldiscpline> findByCompetenceassessment10(
			Object competenceassessment10) {
		return findByProperty(COMPETENCEASSESSMENT10, competenceassessment10);
	}

	public List<Operationaldiscpline> findByCompetenceassessment11(
			Object competenceassessment11) {
		return findByProperty(COMPETENCEASSESSMENT11, competenceassessment11);
	}

	public List<Operationaldiscpline> findByCompetenceassessment12(
			Object competenceassessment12) {
		return findByProperty(COMPETENCEASSESSMENT12, competenceassessment12);
	}

	public List<Operationaldiscpline> findByCompetenceassessment13(
			Object competenceassessment13) {
		return findByProperty(COMPETENCEASSESSMENT13, competenceassessment13);
	}

	public List<Operationaldiscpline> findByMandatorytrainings10(
			Object mandatorytrainings10) {
		return findByProperty(MANDATORYTRAININGS10, mandatorytrainings10);
	}

	public List<Operationaldiscpline> findByMandatorytrainings11(
			Object mandatorytrainings11) {
		return findByProperty(MANDATORYTRAININGS11, mandatorytrainings11);
	}

	public List<Operationaldiscpline> findByMandatorytrainings12(
			Object mandatorytrainings12) {
		return findByProperty(MANDATORYTRAININGS12, mandatorytrainings12);
	}

	public List<Operationaldiscpline> findByMandatorytrainings13(
			Object mandatorytrainings13) {
		return findByProperty(MANDATORYTRAININGS13, mandatorytrainings13);
	}

	public List<Operationaldiscpline> findByCvupdates10(Object cvupdates10) {
		return findByProperty(CVUPDATES10, cvupdates10);
	}

	public List<Operationaldiscpline> findByCvupdates11(Object cvupdates11) {
		return findByProperty(CVUPDATES11, cvupdates11);
	}

	public List<Operationaldiscpline> findByCvupdates12(Object cvupdates12) {
		return findByProperty(CVUPDATES12, cvupdates12);
	}

	public List<Operationaldiscpline> findByCvupdates13(Object cvupdates13) {
		return findByProperty(CVUPDATES13, cvupdates13);
	}

	public List<Operationaldiscpline> findByTimeexpensebooking10(
			Object timeexpensebooking10) {
		return findByProperty(TIMEEXPENSEBOOKING10, timeexpensebooking10);
	}

	public List<Operationaldiscpline> findByTimeexpensebooking11(
			Object timeexpensebooking11) {
		return findByProperty(TIMEEXPENSEBOOKING11, timeexpensebooking11);
	}

	public List<Operationaldiscpline> findByTimeexpensebooking12(
			Object timeexpensebooking12) {
		return findByProperty(TIMEEXPENSEBOOKING12, timeexpensebooking12);
	}

	public List<Operationaldiscpline> findByTimeexpensebooking13(
			Object timeexpensebooking13) {
		return findByProperty(TIMEEXPENSEBOOKING13, timeexpensebooking13);
	}

	/**
	 * Find all Operationaldiscpline entities.
	 * 
	 * @return List<Operationaldiscpline> all Operationaldiscpline entities
	 */
	@SuppressWarnings("unchecked")
	public List<Operationaldiscpline> findAll() {
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

}