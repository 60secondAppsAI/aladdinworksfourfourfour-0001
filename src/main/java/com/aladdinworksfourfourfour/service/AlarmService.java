package com.aladdinworksfourfourfour.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworksfourfourfour.domain.Alarm;
import com.aladdinworksfourfourfour.dto.AlarmDTO;
import com.aladdinworksfourfourfour.dto.AlarmSearchDTO;
import com.aladdinworksfourfourfour.dto.AlarmPageDTO;
import com.aladdinworksfourfourfour.dto.AlarmConvertCriteriaDTO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AlarmService extends GenericService<Alarm, Integer> {

	List<Alarm> findAll();

	ResultDTO addAlarm(AlarmDTO alarmDTO, RequestDTO requestDTO);

	ResultDTO updateAlarm(AlarmDTO alarmDTO, RequestDTO requestDTO);

    Page<Alarm> getAllAlarms(Pageable pageable);

    Page<Alarm> getAllAlarms(Specification<Alarm> spec, Pageable pageable);

	ResponseEntity<AlarmPageDTO> getAlarms(AlarmSearchDTO alarmSearchDTO);
	
	List<AlarmDTO> convertAlarmsToAlarmDTOs(List<Alarm> alarms, AlarmConvertCriteriaDTO convertCriteria);

	AlarmDTO getAlarmDTOById(Integer alarmId);







}





