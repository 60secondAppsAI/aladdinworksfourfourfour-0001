package com.aladdinworksfourfourfour.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.service.impl.GenericServiceImpl;
import com.aladdinworksfourfourfour.dao.SensorDAO;
import com.aladdinworksfourfourfour.domain.Sensor;
import com.aladdinworksfourfourfour.dto.SensorDTO;
import com.aladdinworksfourfourfour.dto.SensorSearchDTO;
import com.aladdinworksfourfourfour.dto.SensorPageDTO;
import com.aladdinworksfourfourfour.dto.SensorConvertCriteriaDTO;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import com.aladdinworksfourfourfour.service.SensorService;
import com.aladdinworksfourfourfour.util.ControllerUtils;





@Service
public class SensorServiceImpl extends GenericServiceImpl<Sensor, Integer> implements SensorService {

    private final static Logger logger = LoggerFactory.getLogger(SensorServiceImpl.class);

	@Autowired
	SensorDAO sensorDao;

	


	@Override
	public GenericDAO<Sensor, Integer> getDAO() {
		return (GenericDAO<Sensor, Integer>) sensorDao;
	}
	
	public List<Sensor> findAll () {
		List<Sensor> sensors = sensorDao.findAll();
		
		return sensors;	
		
	}

	public ResultDTO addSensor(SensorDTO sensorDTO, RequestDTO requestDTO) {

		Sensor sensor = new Sensor();

		sensor.setSensorId(sensorDTO.getSensorId());


		sensor.setType(sensorDTO.getType());


		sensor.setLocation(sensorDTO.getLocation());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		sensor = sensorDao.save(sensor);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Sensor> getAllSensors(Pageable pageable) {
		return sensorDao.findAll(pageable);
	}

	public Page<Sensor> getAllSensors(Specification<Sensor> spec, Pageable pageable) {
		return sensorDao.findAll(spec, pageable);
	}

	public ResponseEntity<SensorPageDTO> getSensors(SensorSearchDTO sensorSearchDTO) {
	
			Integer sensorId = sensorSearchDTO.getSensorId(); 
 			String type = sensorSearchDTO.getType(); 
 			String location = sensorSearchDTO.getLocation(); 
 			String sortBy = sensorSearchDTO.getSortBy();
			String sortOrder = sensorSearchDTO.getSortOrder();
			String searchQuery = sensorSearchDTO.getSearchQuery();
			Integer page = sensorSearchDTO.getPage();
			Integer size = sensorSearchDTO.getSize();

	        Specification<Sensor> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, sensorId, "sensorId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, type, "type"); 
			
			spec = ControllerUtils.andIfNecessary(spec, location, "location"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("type")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("location")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Sensor> sensors = this.getAllSensors(spec, pageable);
		
		//System.out.println(String.valueOf(sensors.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(sensors.getTotalPages()));
		
		List<Sensor> sensorsList = sensors.getContent();
		
		SensorConvertCriteriaDTO convertCriteria = new SensorConvertCriteriaDTO();
		List<SensorDTO> sensorDTOs = this.convertSensorsToSensorDTOs(sensorsList,convertCriteria);
		
		SensorPageDTO sensorPageDTO = new SensorPageDTO();
		sensorPageDTO.setSensors(sensorDTOs);
		sensorPageDTO.setTotalElements(sensors.getTotalElements());
		return ResponseEntity.ok(sensorPageDTO);
	}

	public List<SensorDTO> convertSensorsToSensorDTOs(List<Sensor> sensors, SensorConvertCriteriaDTO convertCriteria) {
		
		List<SensorDTO> sensorDTOs = new ArrayList<SensorDTO>();
		
		for (Sensor sensor : sensors) {
			sensorDTOs.add(convertSensorToSensorDTO(sensor,convertCriteria));
		}
		
		return sensorDTOs;

	}
	
	public SensorDTO convertSensorToSensorDTO(Sensor sensor, SensorConvertCriteriaDTO convertCriteria) {
		
		SensorDTO sensorDTO = new SensorDTO();
		
		sensorDTO.setSensorId(sensor.getSensorId());

	
		sensorDTO.setType(sensor.getType());

	
		sensorDTO.setLocation(sensor.getLocation());

	

		
		return sensorDTO;
	}

	public ResultDTO updateSensor(SensorDTO sensorDTO, RequestDTO requestDTO) {
		
		Sensor sensor = sensorDao.getById(sensorDTO.getSensorId());

		sensor.setSensorId(ControllerUtils.setValue(sensor.getSensorId(), sensorDTO.getSensorId()));

		sensor.setType(ControllerUtils.setValue(sensor.getType(), sensorDTO.getType()));

		sensor.setLocation(ControllerUtils.setValue(sensor.getLocation(), sensorDTO.getLocation()));



        sensor = sensorDao.save(sensor);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SensorDTO getSensorDTOById(Integer sensorId) {
	
		Sensor sensor = sensorDao.getById(sensorId);
			
		
		SensorConvertCriteriaDTO convertCriteria = new SensorConvertCriteriaDTO();
		return(this.convertSensorToSensorDTO(sensor,convertCriteria));
	}







}
