package com.aladdinworksfourfourfour.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.aladdinworksfourfourfour.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.aladdinworksfourfourfour.domain.Alarm;
import com.aladdinworksfourfourfour.dto.AlarmDTO;
import com.aladdinworksfourfourfour.dto.AlarmSearchDTO;
import com.aladdinworksfourfourfour.dto.AlarmPageDTO;
import com.aladdinworksfourfourfour.service.AlarmService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/alarm")
@RestController
public class AlarmController {

	private final static Logger logger = LoggerFactory.getLogger(AlarmController.class);

	@Autowired
	AlarmService alarmService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Alarm> getAll() {

		List<Alarm> alarms = alarmService.findAll();
		
		return alarms;	
	}

	@GetMapping(value = "/{alarmId}")
	@ResponseBody
	public AlarmDTO getAlarm(@PathVariable Integer alarmId) {
		
		return (alarmService.getAlarmDTOById(alarmId));
	}

 	@RequestMapping(value = "/addAlarm", method = RequestMethod.POST)
	public ResponseEntity<?> addAlarm(@RequestBody AlarmDTO alarmDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = alarmService.addAlarm(alarmDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/alarms")
	public ResponseEntity<AlarmPageDTO> getAlarms(AlarmSearchDTO alarmSearchDTO) {
 
		return alarmService.getAlarms(alarmSearchDTO);
	}	

	@RequestMapping(value = "/updateAlarm", method = RequestMethod.POST)
	public ResponseEntity<?> updateAlarm(@RequestBody AlarmDTO alarmDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = alarmService.updateAlarm(alarmDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
