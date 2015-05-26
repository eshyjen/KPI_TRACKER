package com.ericsson.ipm.v1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.ipm.v1.domain.Asset;

/**
 * A data access object (DAO) providing persistence and search support for Asset
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.ericsson.ipm.v1.domain.Asset
 * @author iqbal.hosain.khan@ericsson.com
 */
@Repository("assetDAO")
@Transactional
public class AssetDAOImpl extends BaseDAO<Integer, Asset> implements AssetDAO {
	// property constants
	public static final String ASSETNAME = "assetName";
	public static final String ASSETSHORTDESCRIPTION = "assetShortDescription";
	public static final String PROJECTNAME = "projectName";
	public static final String REGISTEREDINASSETPORTAL = "registeredInAssetPortal";
	public static final String APPROVALSTATUS = "approvalStatus";
	public static final String REUSEDINOTHERPROJECTSNAME = "reusedInOtherProjectsName";
	public static final String EFFORTSAVE = "effortSave";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AssetDAOImpl.class);

	private EntityManager getEntityManager() {
		return entityManager;
	}

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
	 * LOGGER.beginTransaction();
	 * AssetDAO.save(entity);
	 * LOGGER.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Asset entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	/*public Asset save(Asset entity) {
		LOGGER.info("saving Asset instance");
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
	 * Delete a persistent Asset entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * LOGGER.beginTransaction();
	 * AssetDAO.delete(entity);
	 * LOGGER.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Asset entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	/*public void remove(Asset entity) {
		LOGGER.info("deleting Asset instance");
		try {
			entity = getEntityManager().getReference(Asset.class,
					entity.getId());
			getEntityManager().remove(entity);
			LOGGER.info("delete successful");
		} catch (RuntimeException re) {
			LOGGER.info("delete failed"+ re);
			throw re;
		}
	}*/

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
	 * LOGGER.beginTransaction();
	 * entity = AssetDAO.update(entity);
	 * LOGGER.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Asset entity to update
	 * @return Asset the persisted Asset entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	/*public Asset update(Asset entity) {
		LOGGER.info("updating Asset instance");
		try {
			Asset result = getEntityManager().merge(entity);
			LOGGER.info("update successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.info("update failed"+ re);
			throw re;
		}
	}*/

	/*public Asset findById(Integer id) {
		LOGGER.info("finding Asset instance with id: " + id);
		try {
			Asset instance = getEntityManager().find(Asset.class, id);
			return instance;
		} catch (RuntimeException re) {
			LOGGER.info("find failed"+ re);
			throw re;
		}
	}*/

	/**
	 * Find all Asset entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Asset property to query
	 * @param value
	 *            the property value to match
	 * @return List<Asset> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Asset> findByProperty(String propertyName, final Object value) {
		LOGGER.info("finding Asset instance with property: "
				+ propertyName + ", value: " + value);
		try {
			final String queryString = "select model from Asset model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LOGGER.info("find by property name failed"+ re);
			throw re;
		}
	}

	public List<Asset> findByAssetname(Object assetname) {
		return findByProperty(ASSETNAME, assetname);
	}

	public List<Asset> findByAssetshortdescription(Object assetshortdescription) {
		return findByProperty(ASSETSHORTDESCRIPTION, assetshortdescription);
	}

	public List<Asset> findByProjectname(Object projectname) {
		return findByProperty(PROJECTNAME, projectname);
	}

	public List<Asset> findByRegisteredinassetportal(
			Object registeredinassetportal) {
		return findByProperty(REGISTEREDINASSETPORTAL, registeredinassetportal);
	}

	public List<Asset> findByApprovalstatus(Object approvalstatus) {
		return findByProperty(APPROVALSTATUS, approvalstatus);
	}

	public List<Asset> findByReusedinotherprojectsname(
			Object reusedinotherprojectsname) {
		return findByProperty(REUSEDINOTHERPROJECTSNAME,
				reusedinotherprojectsname);
	}

	public List<Asset> findByEffortsave(Object effortsave) {
		return findByProperty(EFFORTSAVE, effortsave);
	}

	/**
	 * Find all Asset entities.
	 * 
	 * @return List<Asset> all Asset entities
	 */
	@SuppressWarnings("unchecked")
	public List<Asset> findAll() {
		LOGGER
				.info("finding all Asset instances");
		try {
			final String queryString = "select model from Asset model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			LOGGER.info("find all failed"+ re);
			throw re;
		}
	}

	@Override
	public void remove(String assetId) {
		Integer asset_id = Integer.parseInt(assetId);
		Asset entity = findById(asset_id);
		remove(entity);
	}

}