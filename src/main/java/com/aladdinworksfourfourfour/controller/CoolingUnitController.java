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

import com.aladdinworksfourfourfour.domain.CoolingUnit;
import com.aladdinworksfourfourfour.dto.CoolingUnitDTO;
import com.aladdinworksfourfourfour.dto.CoolingUnitSearchDTO;
import com.aladdinworksfourfourfour.dto.CoolingUnitPageDTO;
import com.aladdinworksfourfourfour.service.CoolingUnitService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/coolingUnit")
@RestController
public class CoolingUnitController {

	private final static Logger logger = LoggerFactory.getLogger(CoolingUnitController.class);

	@Autowired
	CoolingUnitService coolingUnitService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CoolingUnit> getAll() {

		List<CoolingUnit> coolingUnits = coolingUnitService.findAll();
		
		return coolingUnits;	
	}

	@GetMapping(value = "/{coolingUnitId}")
	@ResponseBody
	public CoolingUnitDTO getCoolingUnit(@PathVariable Integer coolingUnitId) {
		
		return (coolingUnitService.getCoolingUnitDTOById(coolingUnitId));
	}

 	@RequestMapping(value = "/addCoolingUnit", method = RequestMethod.POST)
	public ResponseEntity<?> addCoolingUnit(@RequestBody CoolingUnitDTO coolingUnitDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = coolingUnitService.addCoolingUnit(coolingUnitDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/coolingUnits")
	public ResponseEntity<CoolingUnitPageDTO> getCoolingUnits(CoolingUnitSearchDTO coolingUnitSearchDTO) {
 
		return coolingUnitService.getCoolingUnits(coolingUnitSearchDTO);
	}	

	@RequestMapping(value = "/updateCoolingUnit", method = RequestMethod.POST)
	public ResponseEntity<?> updateCoolingUnit(@RequestBody CoolingUnitDTO coolingUnitDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = coolingUnitService.updateCoolingUnit(coolingUnitDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
