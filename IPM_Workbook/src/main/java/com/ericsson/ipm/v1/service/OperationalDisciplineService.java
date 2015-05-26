package com.ericsson.ipm.v1.service;

import java.util.List;

import com.ericsson.ipm.v1.domain.OperationalDiscipline;
import com.ericsson.ipm.v1.dto.OperationalDisciplineDTO;

public interface OperationalDisciplineService {


	public List<OperationalDiscipline> findByOperationalDisciplineName(
			String operationalDisciplineName);

	public List<OperationalDiscipline> findByFrequency(
			String frequency);

	public List<OperationalDiscipline> findByQuarter(
			String quarter);

	public List<OperationalDiscipline> findByNonCompliance(
			String nonCompliance);


	public OperationalDiscipline save(OperationalDisciplineDTO operationalDisciplineDTO);

	public void remove(OperationalDisciplineDTO operationalDisciplineDTO);

	public OperationalDiscipline update(OperationalDisciplineDTO operationalDisciplineDTO);

	public List<OperationalDiscipline> findAll();

	public OperationalDiscipline getOperationalDisciplineDetail(String id);

}
