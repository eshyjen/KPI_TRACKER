package com.ericsson.ipm.v1.service;

import com.ericsson.ipm.v1.domain.Asset;

public interface AssetService {
	
	public Asset save(Asset entity);
	
	public void remove(Asset entity);

	public Asset update(Asset entity);
	
	public Asset findById(Integer id);
	


}
