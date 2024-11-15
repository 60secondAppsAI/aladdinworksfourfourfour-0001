package com.aladdinworksfourfourfour.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworksfourfourfour.domain.Rack;
import com.aladdinworksfourfourfour.dto.RackDTO;
import com.aladdinworksfourfourfour.dto.RackSearchDTO;
import com.aladdinworksfourfourfour.dto.RackPageDTO;
import com.aladdinworksfourfourfour.dto.RackConvertCriteriaDTO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RackService extends GenericService<Rack, Integer> {

	List<Rack> findAll();

	ResultDTO addRack(RackDTO rackDTO, RequestDTO requestDTO);

	ResultDTO updateRack(RackDTO rackDTO, RequestDTO requestDTO);

    Page<Rack> getAllRacks(Pageable pageable);

    Page<Rack> getAllRacks(Specification<Rack> spec, Pageable pageable);

	ResponseEntity<RackPageDTO> getRacks(RackSearchDTO rackSearchDTO);
	
	List<RackDTO> convertRacksToRackDTOs(List<Rack> racks, RackConvertCriteriaDTO convertCriteria);

	RackDTO getRackDTOById(Integer rackId);







}





