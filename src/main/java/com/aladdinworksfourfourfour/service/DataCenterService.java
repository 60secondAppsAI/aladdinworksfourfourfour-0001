package com.aladdinworksfourfourfour.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworksfourfourfour.domain.DataCenter;
import com.aladdinworksfourfourfour.dto.DataCenterDTO;
import com.aladdinworksfourfourfour.dto.DataCenterSearchDTO;
import com.aladdinworksfourfourfour.dto.DataCenterPageDTO;
import com.aladdinworksfourfourfour.dto.DataCenterConvertCriteriaDTO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DataCenterService extends GenericService<DataCenter, Integer> {

	List<DataCenter> findAll();

	ResultDTO addDataCenter(DataCenterDTO dataCenterDTO, RequestDTO requestDTO);

	ResultDTO updateDataCenter(DataCenterDTO dataCenterDTO, RequestDTO requestDTO);

    Page<DataCenter> getAllDataCenters(Pageable pageable);

    Page<DataCenter> getAllDataCenters(Specification<DataCenter> spec, Pageable pageable);

	ResponseEntity<DataCenterPageDTO> getDataCenters(DataCenterSearchDTO dataCenterSearchDTO);
	
	List<DataCenterDTO> convertDataCentersToDataCenterDTOs(List<DataCenter> dataCenters, DataCenterConvertCriteriaDTO convertCriteria);

	DataCenterDTO getDataCenterDTOById(Integer dataCenterId);







}





