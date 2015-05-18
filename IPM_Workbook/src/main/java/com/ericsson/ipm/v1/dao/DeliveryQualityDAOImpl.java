package com.ericsson.ipm.v1.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.ipm.v1.domain.DeliveryQuality;

@Repository("deliveryQualityDAO")
@Transactional
public class DeliveryQualityDAOImpl extends BaseDAO<Integer, DeliveryQuality> implements DeliveryQualityDAO {

}
