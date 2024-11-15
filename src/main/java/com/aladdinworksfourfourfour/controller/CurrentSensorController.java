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

import com.aladdinworksfourfourfour.domain.CurrentSensor;
import com.aladdinworksfourfourfour.dto.CurrentSensorDTO;
import com.aladdinworksfourfourfour.dto.CurrentSensorSearchDTO;
import com.aladdinworksfourfourfour.dto.CurrentSensorPageDTO;
import com.aladdinworksfourfourfour.service.CurrentSensorService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/currentSensor")
@RestController
public class CurrentSensorController {

	private final static Logger logger = LoggerFactory.getLogger(CurrentSensorController.class);

	@Autowired
	CurrentSensorService currentSensorService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CurrentSensor> getAll() {

		List<CurrentSensor> currentSensors = currentSensorService.findAll();
		
		return currentSensors;	
	}

	@GetMapping(value = "/{currentSensorId}")
	@ResponseBody
	public CurrentSensorDTO getCurrentSensor(@PathVariable Integer currentSensorId) {
		
		return (currentSensorService.getCurrentSensorDTOById(currentSensorId));
	}

 	@RequestMapping(value = "/addCurrentSensor", method = RequestMethod.POST)
	public ResponseEntity<?> addCurrentSensor(@RequestBody CurrentSensorDTO currentSensorDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = currentSensorService.addCurrentSensor(currentSensorDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/currentSensors")
	public ResponseEntity<CurrentSensorPageDTO> getCurrentSensors(CurrentSensorSearchDTO currentSensorSearchDTO) {
 
		return currentSensorService.getCurrentSensors(currentSensorSearchDTO);
	}	

	@RequestMapping(value = "/updateCurrentSensor", method = RequestMethod.POST)
	public ResponseEntity<?> updateCurrentSensor(@RequestBody CurrentSensorDTO currentSensorDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = currentSensorService.updateCurrentSensor(currentSensorDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
