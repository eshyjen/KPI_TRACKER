package com.ericsson.ipm.v1.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.ipm.v1.dao.AssetDAO;
import com.ericsson.ipm.v1.domain.Asset;

@Service("assetService")
@Transactional
public class AssetServiceImpl implements AssetService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetServiceImpl.class);

	
	private AssetDAO assetDAO;
	
	@Override
	public Asset save(Asset entity) {
		return assetDAO.save(entity);
	}

	@Override
	public void remove(String  assetId) {
		assetDAO.remove(assetId);
	}

	@Override
	public Asset update(Asset entity) {
		return assetDAO.update(entity);
	}

	@Override
	public Asset findById(Integer id) {
		return assetDAO.findById(id);
	}

	@Autowired
	public void setAssetDAO(AssetDAO assetDAO) {
		this.assetDAO = assetDAO;
	}
	
	

}
