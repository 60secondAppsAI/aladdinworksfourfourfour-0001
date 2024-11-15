package com.aladdinworksfourfourfour.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworksfourfourfour.domain.Sensor;
import com.aladdinworksfourfourfour.dto.SensorDTO;
import com.aladdinworksfourfourfour.dto.SensorSearchDTO;
import com.aladdinworksfourfourfour.dto.SensorPageDTO;
import com.aladdinworksfourfourfour.dto.SensorConvertCriteriaDTO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SensorService extends GenericService<Sensor, Integer> {

	List<Sensor> findAll();

	ResultDTO addSensor(SensorDTO sensorDTO, RequestDTO requestDTO);

	ResultDTO updateSensor(SensorDTO sensorDTO, RequestDTO requestDTO);

    Page<Sensor> getAllSensors(Pageable pageable);

    Page<Sensor> getAllSensors(Specification<Sensor> spec, Pageable pageable);

	ResponseEntity<SensorPageDTO> getSensors(SensorSearchDTO sensorSearchDTO);
	
	List<SensorDTO> convertSensorsToSensorDTOs(List<Sensor> sensors, SensorConvertCriteriaDTO convertCriteria);

	SensorDTO getSensorDTOById(Integer sensorId);







}





