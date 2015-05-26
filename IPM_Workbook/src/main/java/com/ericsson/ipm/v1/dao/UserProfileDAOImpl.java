package com.ericsson.ipm.v1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.ipm.v1.domain.Asset;
import com.ericsson.ipm.v1.domain.DeliveryQuality;
import com.ericsson.ipm.v1.domain.KPI;
import com.ericsson.ipm.v1.domain.KPIRoleAssignment;
import com.ericsson.ipm.v1.domain.OperationalDiscipline;
import com.ericsson.ipm.v1.domain.Role;
import com.ericsson.ipm.v1.domain.UserProfile;
import com.ericsson.ipm.v1.domain.UserRoleAssignment;

/**
 * A data access object (DAO) providing persistence and search support for
 * Userprofile entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 *
 * @see com.ericsson.ipm.v1.domain.UserProfile
 * @author iqbal.hosain.khan@ericsson.com
 */

@Repository("userProfileDAO")
@Transactional
public class UserProfileDAOImpl extends BaseDAO<Integer, UserProfile> implements UserProfileDAO {
	// property constants
	public static final String SIGNUNID = "signunId";
	public static final String EMPID = "employeeId";
	public static final String EMAILID = "emailId";
	public static final String COSTCENTER = "costCenter";
	public static final String TOTALYEARSOFEXPERIENCE = "totalYearsOfExperience";
	public static final String TOTALITEXPERIENCE = "totalITExperience";
	public static final String TOTALERICSSONEXPERIENCEINMONTHS = "totalYearsOfExperience";
	public static final String EDUNATIONALQUALIFICATION = "educationalQualification";
	public static final String PREVIOUSLINEMANEGER = "previousLineManeger";
	public static final String CURRENTLINEMANAGER = "currentLineManager";
	public static final String JOBROLE = "jobRole";
	public static final String JOBSTAGE = "jobStage";
	public static final String MANHOURRATE = "manHourRate";
	public static final String LASTYEARIPMRATING = "lastYearIPMRating";
	public static final String YEAROFLASTPROMOTION = "yearOfLastPromotion";
	public static final String PREVIOUSORGANISATION = "previousOrganisation";
	public static final String YEAROFIPM = "yearOfIPM";


	private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileDAOImpl.class);

	private EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Perform an initial save of a previously unsaved Userprofile entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UserprofileDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            Userprofile entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	/*public UserProfile save(UserProfile entity) {
		LOGGER.info("saving Userprofile instance");
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
	 * Delete a persistent Userprofile entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UserprofileDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 *
	 * @param entity
	 *            Userprofile entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	/*public void remove(UserProfile entity) {
		LOGGER.info("deleting Userprofile instance");
		try {
			entity = getEntityManager().getReference(UserProfile.class,
					entity.getId());
			getEntityManager().remove(entity);
			LOGGER.info("delete successful");
		} catch (RuntimeException re) {
			LOGGER.info("delete failed"+ re);
			throw re;
		}
	}*/

	/**
	 * Persist a previously saved Userprofile entity and return it or a copy of
	 * it to the sender. A copy of the Userprofile entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 *
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = UserprofileDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 *
	 * @param entity
	 *            Userprofile entity to update
	 * @return Userprofile the persisted Userprofile entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	/*public UserProfile update(UserProfile entity) {
		LOGGER.info("updating Userprofile instance");
		try {
			UserProfile result = getEntityManager().merge(entity);
			LOGGER.info("update successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.info("update failed"+ re);
			throw re;
		}
	}

	public UserProfile findById(Integer id) {
		LOGGER.info("finding Userprofile instance with id: " + id);
		try {
			UserProfile instance = getEntityManager().find(UserProfile.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			LOGGER.info("find failed"+ re);
			throw re;
		}
	}*/

	/**
	 * Find all Userprofile entities with a specific property value.
	 *
	 * @param propertyName
	 *            the name of the Userprofile property to query
	 * @param value
	 *            the property value to match
	 * @return List<Userprofile> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<UserProfile> findByProperty(String propertyName,
			final Object value) {
		LOGGER.info("finding Userprofile instance with property: "
				+ propertyName + ", value: " + value);
		try {
			final String queryString = "select model from UserProfile model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LOGGER.info("find by property name failed"+ re);
			throw re;
		}
	}

	public List<UserProfile> findBySignunid(Object signunid) {
		return findByProperty(SIGNUNID, signunid);
	}

	public List<UserProfile> findByEmpid(Object empid) {
		return findByProperty(EMPID, empid);
	}

	public List<UserProfile> findByEmailid(Object emailid) {
		return findByProperty(EMAILID, emailid);
	}

	public List<UserProfile> findByCostcenter(Object costcenter) {
		return findByProperty(COSTCENTER, costcenter);
	}

	public List<UserProfile> findByTotalyearsofexperience(
			Object totalyearsofexperience) {
		return findByProperty(TOTALYEARSOFEXPERIENCE, totalyearsofexperience);
	}

	public List<UserProfile> findByTotalitexperience(Object totalitexperience) {
		return findByProperty(TOTALITEXPERIENCE, totalitexperience);
	}

	public List<UserProfile> findByTotalericssonexperienceinmonths(
			Object totalericssonexperienceinmonths) {
		return findByProperty(TOTALERICSSONEXPERIENCEINMONTHS,
				totalericssonexperienceinmonths);
	}

	public List<UserProfile> findByEdunationalqualification(
			Object edunationalqualification) {
		return findByProperty(EDUNATIONALQUALIFICATION,
				edunationalqualification);
	}

	public List<UserProfile> findByPreviouslinemaneger(
			Object previouslinemaneger) {
		return findByProperty(PREVIOUSLINEMANEGER, previouslinemaneger);
	}

	public List<UserProfile> findByCurrentlinemanager(Object currentlinemanager) {
		return findByProperty(CURRENTLINEMANAGER, currentlinemanager);
	}

	public List<UserProfile> findByJobrole(Object jobrole) {
		return findByProperty(JOBROLE, jobrole);
	}

	public List<UserProfile> findByJobstage(Object jobstage) {
		return findByProperty(JOBSTAGE, jobstage);
	}

	public List<UserProfile> findByManhourrate(Object manhourrate) {
		return findByProperty(MANHOURRATE, manhourrate);
	}

	public List<UserProfile> findByLastyearipmrating(Object lastyearipmrating) {
		return findByProperty(LASTYEARIPMRATING, lastyearipmrating);
	}

	public List<UserProfile> findByYearoflastpromotion(
			Object yearoflastpromotion) {
		return findByProperty(YEAROFLASTPROMOTION, yearoflastpromotion);
	}

	public List<UserProfile> findByPreviousorganisation(
			Object previousorganisation) {
		return findByProperty(PREVIOUSORGANISATION, previousorganisation);
	}

	public List<UserProfile> findByYearofipm(Object yearofipm) {
		return findByProperty(YEAROFIPM, yearofipm);
	}

	/**
	 * Find all Userprofile entities.
	 *
	 * @return List<Userprofile> all Userprofile entities
	 */
	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll() {
		LOGGER.info("finding all Userprofile instances");
		try {
			final String queryString = "select model from UserProfile model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LOGGER.info("find all failed"+ re);
			throw re;
		}
	}

	public List<UserProfile> findBySignunidWithRole(Object signunid) {
		List<UserProfile> userProfiles = findByProperty(SIGNUNID, signunid);
		for(UserProfile profile : userProfiles){
			for(UserRoleAssignment  userRoleAssignment : profile.getRoleAssignments()){
				Role role = userRoleAssignment.getRole();
				LOGGER.debug("role.getCode() : "+ role.getCode());
				for(KPIRoleAssignment KPIRoleAssignment : role.getKpiRoleAssignments()) {
					KPI kpi = KPIRoleAssignment.getKpi();
					LOGGER.debug("kpi.getKpiValue() : "+ kpi.getKpiValue());
				}

			}

		}
		return userProfiles;
	}

	@SuppressWarnings("unchecked")
	public List<UserProfile> findByIdWithAsset(Object id) {

		try {
			//final String queryString = "from UserProfile model left join fetch model.assets where model.id= :propertyValue";
			final String queryString = "from UserProfile model where model.id= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", id);
			List<UserProfile> userProfiles = query.getResultList();
			for(UserProfile profile : userProfiles){
				List<Asset> assets = profile.getAssets();
				LOGGER.debug("assets : "+ assets);
			}
			return userProfiles;
		} catch (RuntimeException re) {
			LOGGER.info("UserProfile find by property name failed"+ re);
			throw re;
		}
	}

	@Override
	public List<UserProfile> findByIdWithDeliveryQuality(Object id) {
		try {
			//final String queryString = "from UserProfile model left join fetch model.assets where model.id= :propertyValue";
			final String queryString = "from UserProfile model where model.id= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", id);
			List<UserProfile> userProfiles = query.getResultList();
			for(UserProfile profile : userProfiles){
				List<DeliveryQuality> deliveryQualities = profile.getDeliveryQualities();
				LOGGER.debug("deliveryQualities : "+ deliveryQualities);
			}
			return userProfiles;
		} catch (RuntimeException re) {
			LOGGER.info("UserProfile find by property name failed"+ re);
			throw re;
		}
	}

	@Override
	public List<UserProfile> findByIdWithOperationalDiscipline(Object id) {
		try {
			//final String queryString = "from UserProfile model left join fetch model.assets where model.id= :propertyValue";
			final String queryString = "from UserProfile model where model.id= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", id);
			List<UserProfile> userProfiles = query.getResultList();
			for(UserProfile profile : userProfiles){
				List<OperationalDiscipline> operationalDiscipline = profile.getOperationaldiscplines();
				LOGGER.debug("deliveryQualities : "+ operationalDiscipline);
			}
			return userProfiles;
		} catch (RuntimeException re) {
			LOGGER.info("UserProfile find by property name failed"+ re);
			throw re;
		}
	}

}