package com.aladdinworksfourfourfour.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworksfourfourfour.domain.Switch;
import com.aladdinworksfourfourfour.dto.SwitchDTO;
import com.aladdinworksfourfourfour.dto.SwitchSearchDTO;
import com.aladdinworksfourfourfour.dto.SwitchPageDTO;
import com.aladdinworksfourfourfour.dto.SwitchConvertCriteriaDTO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SwitchService extends GenericService<Switch, Integer> {

	List<Switch> findAll();

	ResultDTO addSwitch(SwitchDTO switchDTO, RequestDTO requestDTO);

	ResultDTO updateSwitch(SwitchDTO switchDTO, RequestDTO requestDTO);

    Page<Switch> getAllSwitchs(Pageable pageable);

    Page<Switch> getAllSwitchs(Specification<Switch> spec, Pageable pageable);

	ResponseEntity<SwitchPageDTO> getSwitchs(SwitchSearchDTO switchSearchDTO);
	
	List<SwitchDTO> convertSwitchsToSwitchDTOs(List<Switch> switchs, SwitchConvertCriteriaDTO convertCriteria);

	SwitchDTO getSwitchDTOById(Integer switchId);







}





