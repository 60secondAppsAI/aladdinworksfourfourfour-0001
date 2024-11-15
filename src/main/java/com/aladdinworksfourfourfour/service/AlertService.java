package com.aladdinworksfourfourfour.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworksfourfourfour.domain.Alert;
import com.aladdinworksfourfourfour.dto.AlertDTO;
import com.aladdinworksfourfourfour.dto.AlertSearchDTO;
import com.aladdinworksfourfourfour.dto.AlertPageDTO;
import com.aladdinworksfourfourfour.dto.AlertConvertCriteriaDTO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AlertService extends GenericService<Alert, Integer> {

	List<Alert> findAll();

	ResultDTO addAlert(AlertDTO alertDTO, RequestDTO requestDTO);

	ResultDTO updateAlert(AlertDTO alertDTO, RequestDTO requestDTO);

    Page<Alert> getAllAlerts(Pageable pageable);

    Page<Alert> getAllAlerts(Specification<Alert> spec, Pageable pageable);

	ResponseEntity<AlertPageDTO> getAlerts(AlertSearchDTO alertSearchDTO);
	
	List<AlertDTO> convertAlertsToAlertDTOs(List<Alert> alerts, AlertConvertCriteriaDTO convertCriteria);

	AlertDTO getAlertDTOById(Integer alertId);







}





