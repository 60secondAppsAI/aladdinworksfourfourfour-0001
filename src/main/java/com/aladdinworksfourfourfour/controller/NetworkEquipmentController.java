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

import com.aladdinworksfourfourfour.domain.NetworkEquipment;
import com.aladdinworksfourfourfour.dto.NetworkEquipmentDTO;
import com.aladdinworksfourfourfour.dto.NetworkEquipmentSearchDTO;
import com.aladdinworksfourfourfour.dto.NetworkEquipmentPageDTO;
import com.aladdinworksfourfourfour.service.NetworkEquipmentService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/networkEquipment")
@RestController
public class NetworkEquipmentController {

	private final static Logger logger = LoggerFactory.getLogger(NetworkEquipmentController.class);

	@Autowired
	NetworkEquipmentService networkEquipmentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<NetworkEquipment> getAll() {

		List<NetworkEquipment> networkEquipments = networkEquipmentService.findAll();
		
		return networkEquipments;	
	}

	@GetMapping(value = "/{networkEquipmentId}")
	@ResponseBody
	public NetworkEquipmentDTO getNetworkEquipment(@PathVariable Integer networkEquipmentId) {
		
		return (networkEquipmentService.getNetworkEquipmentDTOById(networkEquipmentId));
	}

 	@RequestMapping(value = "/addNetworkEquipment", method = RequestMethod.POST)
	public ResponseEntity<?> addNetworkEquipment(@RequestBody NetworkEquipmentDTO networkEquipmentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = networkEquipmentService.addNetworkEquipment(networkEquipmentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/networkEquipments")
	public ResponseEntity<NetworkEquipmentPageDTO> getNetworkEquipments(NetworkEquipmentSearchDTO networkEquipmentSearchDTO) {
 
		return networkEquipmentService.getNetworkEquipments(networkEquipmentSearchDTO);
	}	

	@RequestMapping(value = "/updateNetworkEquipment", method = RequestMethod.POST)
	public ResponseEntity<?> updateNetworkEquipment(@RequestBody NetworkEquipmentDTO networkEquipmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = networkEquipmentService.updateNetworkEquipment(networkEquipmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
