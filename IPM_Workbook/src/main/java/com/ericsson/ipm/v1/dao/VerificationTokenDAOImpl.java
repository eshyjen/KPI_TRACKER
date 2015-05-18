package com.ericsson.ipm.v1.dao;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.ipm.v1.domain.UserProfile;
import com.ericsson.ipm.v1.domain.VerificationToken;

@Repository("verificationTokenDAO")
@Transactional
public class VerificationTokenDAOImpl extends BaseDAO<Integer, VerificationToken> implements VerificationTokenDAO {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(VerificationTokenDAOImpl.class);
	
	private EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public VerificationToken findByToken(String token) {
		return findByUniqueKey("token", token);
	}

	@Override
	public VerificationToken findByUser(UserProfile user) {
		return findByUniqueKey("user", user);
	}

	
	
}
