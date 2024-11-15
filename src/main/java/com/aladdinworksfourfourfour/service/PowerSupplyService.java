package com.aladdinworksfourfourfour.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworksfourfourfour.domain.PowerSupply;
import com.aladdinworksfourfourfour.dto.PowerSupplyDTO;
import com.aladdinworksfourfourfour.dto.PowerSupplySearchDTO;
import com.aladdinworksfourfourfour.dto.PowerSupplyPageDTO;
import com.aladdinworksfourfourfour.dto.PowerSupplyConvertCriteriaDTO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PowerSupplyService extends GenericService<PowerSupply, Integer> {

	List<PowerSupply> findAll();

	ResultDTO addPowerSupply(PowerSupplyDTO powerSupplyDTO, RequestDTO requestDTO);

	ResultDTO updatePowerSupply(PowerSupplyDTO powerSupplyDTO, RequestDTO requestDTO);

    Page<PowerSupply> getAllPowerSupplys(Pageable pageable);

    Page<PowerSupply> getAllPowerSupplys(Specification<PowerSupply> spec, Pageable pageable);

	ResponseEntity<PowerSupplyPageDTO> getPowerSupplys(PowerSupplySearchDTO powerSupplySearchDTO);
	
	List<PowerSupplyDTO> convertPowerSupplysToPowerSupplyDTOs(List<PowerSupply> powerSupplys, PowerSupplyConvertCriteriaDTO convertCriteria);

	PowerSupplyDTO getPowerSupplyDTOById(Integer powerSupplyId);







}





