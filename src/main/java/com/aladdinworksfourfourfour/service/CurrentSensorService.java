package com.aladdinworksfourfourfour.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworksfourfourfour.domain.CurrentSensor;
import com.aladdinworksfourfourfour.dto.CurrentSensorDTO;
import com.aladdinworksfourfourfour.dto.CurrentSensorSearchDTO;
import com.aladdinworksfourfourfour.dto.CurrentSensorPageDTO;
import com.aladdinworksfourfourfour.dto.CurrentSensorConvertCriteriaDTO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CurrentSensorService extends GenericService<CurrentSensor, Integer> {

	List<CurrentSensor> findAll();

	ResultDTO addCurrentSensor(CurrentSensorDTO currentSensorDTO, RequestDTO requestDTO);

	ResultDTO updateCurrentSensor(CurrentSensorDTO currentSensorDTO, RequestDTO requestDTO);

    Page<CurrentSensor> getAllCurrentSensors(Pageable pageable);

    Page<CurrentSensor> getAllCurrentSensors(Specification<CurrentSensor> spec, Pageable pageable);

	ResponseEntity<CurrentSensorPageDTO> getCurrentSensors(CurrentSensorSearchDTO currentSensorSearchDTO);
	
	List<CurrentSensorDTO> convertCurrentSensorsToCurrentSensorDTOs(List<CurrentSensor> currentSensors, CurrentSensorConvertCriteriaDTO convertCriteria);

	CurrentSensorDTO getCurrentSensorDTOById(Integer currentSensorId);







}





