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

import com.aladdinworksfourfourfour.domain.Switch;
import com.aladdinworksfourfourfour.dto.SwitchDTO;
import com.aladdinworksfourfourfour.dto.SwitchSearchDTO;
import com.aladdinworksfourfourfour.dto.SwitchPageDTO;
import com.aladdinworksfourfourfour.service.SwitchService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/switch")
@RestController
public class SwitchController {

	private final static Logger logger = LoggerFactory.getLogger(SwitchController.class);

	@Autowired
	SwitchService switchService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Switch> getAll() {

		List<Switch> switchs = switchService.findAll();
		
		return switchs;	
	}

	@GetMapping(value = "/{switchId}")
	@ResponseBody
	public SwitchDTO getSwitch(@PathVariable Integer switchId) {
		
		return (switchService.getSwitchDTOById(switchId));
	}

 	@RequestMapping(value = "/addSwitch", method = RequestMethod.POST)
	public ResponseEntity<?> addSwitch(@RequestBody SwitchDTO switchDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = switchService.addSwitch(switchDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/switchs")
	public ResponseEntity<SwitchPageDTO> getSwitchs(SwitchSearchDTO switchSearchDTO) {
 
		return switchService.getSwitchs(switchSearchDTO);
	}	

	@RequestMapping(value = "/updateSwitch", method = RequestMethod.POST)
	public ResponseEntity<?> updateSwitch(@RequestBody SwitchDTO switchDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = switchService.updateSwitch(switchDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
