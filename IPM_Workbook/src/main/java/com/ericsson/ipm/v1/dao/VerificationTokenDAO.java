package com.ericsson.ipm.v1.dao;

import com.ericsson.ipm.v1.domain.UserProfile;
import com.ericsson.ipm.v1.domain.VerificationToken;

public interface VerificationTokenDAO {

	public VerificationToken findByToken(String token);

    public VerificationToken findByUser(UserProfile user);
    
    
    public VerificationToken save(VerificationToken entity);

	
	public void remove(VerificationToken entity);

	
	public VerificationToken update(VerificationToken entity);

	public VerificationToken findById(Integer id);
	
}
