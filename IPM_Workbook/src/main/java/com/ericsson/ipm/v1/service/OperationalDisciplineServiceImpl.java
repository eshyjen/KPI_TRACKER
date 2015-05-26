package com.ericsson.ipm.v1.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ericsson.ipm.v1.dao.OperationalDisciplineDAO;
import com.ericsson.ipm.v1.domain.OperationalDiscipline;
import com.ericsson.ipm.v1.dto.OperationalDisciplineDTO;


@Service("operationalDisciplineService")
@javax.transaction.Transactional
public class OperationalDisciplineServiceImpl implements OperationalDisciplineService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperationalDisciplineServiceImpl.class);

	private OperationalDisciplineDAO operationalDisciplineDAO;

	public List<OperationalDiscipline> findByOperationalDisciplineName(
			String operationalDisciplineName) {
		LOGGER.debug("operationalDisciplineName : "+operationalDisciplineName);
		return operationalDisciplineDAO.findByOperationalDisciplineName(operationalDisciplineName);
	}

	public List<OperationalDiscipline> findByFrequency(
			String frequency) {
		LOGGER.debug("frequency : "+frequency);
		return operationalDisciplineDAO.findByFrequency(frequency);
	}

	public List<OperationalDiscipline> findByQuarter(
			String quarter) {
		LOGGER.debug("quarter : "+quarter);
		return operationalDisciplineDAO.findByQuarter(quarter);
	}

	@Override
	public OperationalDiscipline save(OperationalDisciplineDTO operationalDisciplineDTO) {
		// TODO Auto-generated method stub
		OperationalDiscipline operationalDiscipline = new OperationalDiscipline();
		operationalDiscipline.setOperationalDisciplineName(operationalDisciplineDTO.getOperationalDisciplineName());
		operationalDiscipline.setFrequency(operationalDisciplineDTO.getFrequency());
		operationalDiscipline.setQuarter(operationalDisciplineDTO.getQuarter());
		operationalDiscipline.setNonCompliance(operationalDisciplineDTO.getNonCompliance());
		return operationalDisciplineDAO.save(operationalDiscipline);
	}

	@Override
	public void remove(OperationalDisciplineDTO operationalDisciplineDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public OperationalDiscipline update(OperationalDisciplineDTO operationalDisciplineDTO) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see com.ericsson.ipm.v1.service.OperationalDisciplineService#getOperationalDisciplineDetail(java.lang.String)
	 */
	@Override
	public OperationalDiscipline getOperationalDisciplineDetail(String id) {
		// TODO Auto-generated method stub
		return operationalDisciplineDAO.getOperationalDisciplineDetail(id);
	}


	@Override
	public List<OperationalDiscipline> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OperationalDiscipline> findByNonCompliance(
			String nonCompliance) {
		LOGGER.debug("nonCompliance : "+nonCompliance);
		return operationalDisciplineDAO.findByNonCompliance(nonCompliance);
	}

	/**
	 * @param operationalDisciplineDAO the operationalDisciplineDAO to set
	 */
	@Autowired
	public void setOperationalDisciplineDAO(
			OperationalDisciplineDAO operationalDisciplineDAO) {
		this.operationalDisciplineDAO = operationalDisciplineDAO;
	}


}
