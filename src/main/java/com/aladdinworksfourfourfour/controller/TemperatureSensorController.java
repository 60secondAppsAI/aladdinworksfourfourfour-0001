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

import com.aladdinworksfourfourfour.domain.TemperatureSensor;
import com.aladdinworksfourfourfour.dto.TemperatureSensorDTO;
import com.aladdinworksfourfourfour.dto.TemperatureSensorSearchDTO;
import com.aladdinworksfourfourfour.dto.TemperatureSensorPageDTO;
import com.aladdinworksfourfourfour.service.TemperatureSensorService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/temperatureSensor")
@RestController
public class TemperatureSensorController {

	private final static Logger logger = LoggerFactory.getLogger(TemperatureSensorController.class);

	@Autowired
	TemperatureSensorService temperatureSensorService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<TemperatureSensor> getAll() {

		List<TemperatureSensor> temperatureSensors = temperatureSensorService.findAll();
		
		return temperatureSensors;	
	}

	@GetMapping(value = "/{temperatureSensorId}")
	@ResponseBody
	public TemperatureSensorDTO getTemperatureSensor(@PathVariable Integer temperatureSensorId) {
		
		return (temperatureSensorService.getTemperatureSensorDTOById(temperatureSensorId));
	}

 	@RequestMapping(value = "/addTemperatureSensor", method = RequestMethod.POST)
	public ResponseEntity<?> addTemperatureSensor(@RequestBody TemperatureSensorDTO temperatureSensorDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = temperatureSensorService.addTemperatureSensor(temperatureSensorDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/temperatureSensors")
	public ResponseEntity<TemperatureSensorPageDTO> getTemperatureSensors(TemperatureSensorSearchDTO temperatureSensorSearchDTO) {
 
		return temperatureSensorService.getTemperatureSensors(temperatureSensorSearchDTO);
	}	

	@RequestMapping(value = "/updateTemperatureSensor", method = RequestMethod.POST)
	public ResponseEntity<?> updateTemperatureSensor(@RequestBody TemperatureSensorDTO temperatureSensorDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = temperatureSensorService.updateTemperatureSensor(temperatureSensorDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
