package com.aladdinworksfourfourfour.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworksfourfourfour.domain.MonitoringPoint;
import com.aladdinworksfourfourfour.dto.MonitoringPointDTO;
import com.aladdinworksfourfourfour.dto.MonitoringPointSearchDTO;
import com.aladdinworksfourfourfour.dto.MonitoringPointPageDTO;
import com.aladdinworksfourfourfour.dto.MonitoringPointConvertCriteriaDTO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MonitoringPointService extends GenericService<MonitoringPoint, Integer> {

	List<MonitoringPoint> findAll();

	ResultDTO addMonitoringPoint(MonitoringPointDTO monitoringPointDTO, RequestDTO requestDTO);

	ResultDTO updateMonitoringPoint(MonitoringPointDTO monitoringPointDTO, RequestDTO requestDTO);

    Page<MonitoringPoint> getAllMonitoringPoints(Pageable pageable);

    Page<MonitoringPoint> getAllMonitoringPoints(Specification<MonitoringPoint> spec, Pageable pageable);

	ResponseEntity<MonitoringPointPageDTO> getMonitoringPoints(MonitoringPointSearchDTO monitoringPointSearchDTO);
	
	List<MonitoringPointDTO> convertMonitoringPointsToMonitoringPointDTOs(List<MonitoringPoint> monitoringPoints, MonitoringPointConvertCriteriaDTO convertCriteria);

	MonitoringPointDTO getMonitoringPointDTOById(Integer monitoringPointId);







}





