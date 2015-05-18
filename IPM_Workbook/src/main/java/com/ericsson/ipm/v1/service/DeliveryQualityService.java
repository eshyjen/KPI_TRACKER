package com.ericsson.ipm.v1.service;

import com.ericsson.ipm.v1.domain.DeliveryQuality;

public interface DeliveryQualityService {

	
	public DeliveryQuality save(DeliveryQuality entity);
	
	public void remove(DeliveryQuality entity);

	public DeliveryQuality update(DeliveryQuality entity);
	
	public DeliveryQuality findById(Integer id);
}
