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
import com.aladdinworksfourfourfour.dao.AlarmDAO;
import com.aladdinworksfourfourfour.domain.Alarm;
import com.aladdinworksfourfourfour.dto.AlarmDTO;
import com.aladdinworksfourfourfour.dto.AlarmSearchDTO;
import com.aladdinworksfourfourfour.dto.AlarmPageDTO;
import com.aladdinworksfourfourfour.dto.AlarmConvertCriteriaDTO;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import com.aladdinworksfourfourfour.service.AlarmService;
import com.aladdinworksfourfourfour.util.ControllerUtils;





@Service
public class AlarmServiceImpl extends GenericServiceImpl<Alarm, Integer> implements AlarmService {

    private final static Logger logger = LoggerFactory.getLogger(AlarmServiceImpl.class);

	@Autowired
	AlarmDAO alarmDao;

	


	@Override
	public GenericDAO<Alarm, Integer> getDAO() {
		return (GenericDAO<Alarm, Integer>) alarmDao;
	}
	
	public List<Alarm> findAll () {
		List<Alarm> alarms = alarmDao.findAll();
		
		return alarms;	
		
	}

	public ResultDTO addAlarm(AlarmDTO alarmDTO, RequestDTO requestDTO) {

		Alarm alarm = new Alarm();

		alarm.setAlarmId(alarmDTO.getAlarmId());


		alarm.setDescription(alarmDTO.getDescription());


		alarm.setResolved(alarmDTO.getResolved());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		alarm = alarmDao.save(alarm);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Alarm> getAllAlarms(Pageable pageable) {
		return alarmDao.findAll(pageable);
	}

	public Page<Alarm> getAllAlarms(Specification<Alarm> spec, Pageable pageable) {
		return alarmDao.findAll(spec, pageable);
	}

	public ResponseEntity<AlarmPageDTO> getAlarms(AlarmSearchDTO alarmSearchDTO) {
	
			Integer alarmId = alarmSearchDTO.getAlarmId(); 
 			String description = alarmSearchDTO.getDescription(); 
 			String resolved = alarmSearchDTO.getResolved(); 
 			String sortBy = alarmSearchDTO.getSortBy();
			String sortOrder = alarmSearchDTO.getSortOrder();
			String searchQuery = alarmSearchDTO.getSearchQuery();
			Integer page = alarmSearchDTO.getPage();
			Integer size = alarmSearchDTO.getSize();

	        Specification<Alarm> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, alarmId, "alarmId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
			spec = ControllerUtils.andIfNecessary(spec, resolved, "resolved"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("resolved")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Alarm> alarms = this.getAllAlarms(spec, pageable);
		
		//System.out.println(String.valueOf(alarms.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(alarms.getTotalPages()));
		
		List<Alarm> alarmsList = alarms.getContent();
		
		AlarmConvertCriteriaDTO convertCriteria = new AlarmConvertCriteriaDTO();
		List<AlarmDTO> alarmDTOs = this.convertAlarmsToAlarmDTOs(alarmsList,convertCriteria);
		
		AlarmPageDTO alarmPageDTO = new AlarmPageDTO();
		alarmPageDTO.setAlarms(alarmDTOs);
		alarmPageDTO.setTotalElements(alarms.getTotalElements());
		return ResponseEntity.ok(alarmPageDTO);
	}

	public List<AlarmDTO> convertAlarmsToAlarmDTOs(List<Alarm> alarms, AlarmConvertCriteriaDTO convertCriteria) {
		
		List<AlarmDTO> alarmDTOs = new ArrayList<AlarmDTO>();
		
		for (Alarm alarm : alarms) {
			alarmDTOs.add(convertAlarmToAlarmDTO(alarm,convertCriteria));
		}
		
		return alarmDTOs;

	}
	
	public AlarmDTO convertAlarmToAlarmDTO(Alarm alarm, AlarmConvertCriteriaDTO convertCriteria) {
		
		AlarmDTO alarmDTO = new AlarmDTO();
		
		alarmDTO.setAlarmId(alarm.getAlarmId());

	
		alarmDTO.setDescription(alarm.getDescription());

	
		alarmDTO.setResolved(alarm.getResolved());

	

		
		return alarmDTO;
	}

	public ResultDTO updateAlarm(AlarmDTO alarmDTO, RequestDTO requestDTO) {
		
		Alarm alarm = alarmDao.getById(alarmDTO.getAlarmId());

		alarm.setAlarmId(ControllerUtils.setValue(alarm.getAlarmId(), alarmDTO.getAlarmId()));

		alarm.setDescription(ControllerUtils.setValue(alarm.getDescription(), alarmDTO.getDescription()));

		alarm.setResolved(ControllerUtils.setValue(alarm.getResolved(), alarmDTO.getResolved()));



        alarm = alarmDao.save(alarm);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AlarmDTO getAlarmDTOById(Integer alarmId) {
	
		Alarm alarm = alarmDao.getById(alarmId);
			
		
		AlarmConvertCriteriaDTO convertCriteria = new AlarmConvertCriteriaDTO();
		return(this.convertAlarmToAlarmDTO(alarm,convertCriteria));
	}







}
