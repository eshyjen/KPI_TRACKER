package com.ericsson.ipm.v1.dao;

import java.util.List;

import com.ericsson.ipm.v1.domain.UserProfile;

/**
 * Interface for UserprofileDAO.
 *
 * @author iqbal.hosain.khan@ericsson.com
 */

public interface UserProfileDAO {


	/**
	 * Find all Userprofile entities with a specific property value.
	 *
	 * @param propertyName
	 *            the name of the Userprofile property to query
	 * @param value
	 *            the property value to match
	 * @return List<Userprofile> found by query
	 */
	public List<UserProfile> findByProperty(String propertyName, Object value);

	public List<UserProfile> findBySignunid(Object signunid);

	public List<UserProfile> findByEmpid(Object empid);

	public List<UserProfile> findByEmailid(Object emailid);

	public List<UserProfile> findByCostcenter(Object costcenter);

	public List<UserProfile> findByTotalyearsofexperience(
			Object totalyearsofexperience);

	public List<UserProfile> findByTotalitexperience(Object totalitexperience);

	public List<UserProfile> findByTotalericssonexperienceinmonths(
			Object totalericssonexperienceinmonths);

	public List<UserProfile> findByEdunationalqualification(
			Object edunationalqualification);

	public List<UserProfile> findByPreviouslinemaneger(
			Object previouslinemaneger);

	public List<UserProfile> findByCurrentlinemanager(Object currentlinemanager);

	public List<UserProfile> findByJobrole(Object jobrole);

	public List<UserProfile> findByJobstage(Object jobstage);

	public List<UserProfile> findByManhourrate(Object manhourrate);

	public List<UserProfile> findByLastyearipmrating(Object lastyearipmrating);

	public List<UserProfile> findByYearoflastpromotion(
			Object yearoflastpromotion);

	public List<UserProfile> findByPreviousorganisation(
			Object previousorganisation);

	public List<UserProfile> findByYearofipm(Object yearofipm);

	/**
	 * Find all Userprofile entities.
	 *
	 * @return List<Userprofile> all Userprofile entities
	 */
	public List<UserProfile> findAll();

	public UserProfile save(UserProfile entity);

	public void remove(UserProfile entity);


	public UserProfile update(UserProfile entity);

	public UserProfile findById(Integer id);

	public List<UserProfile> findBySignunidWithRole(Object signunid);

	public List<UserProfile> findByIdWithAsset(Object id);

	public UserProfile getRefById(final Integer userId);

	public List<UserProfile> findByIdWithDeliveryQuality(Object id);

	public List<UserProfile> findByIdWithOperationalDiscipline(Object id);

}