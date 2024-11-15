package com.aladdinworksfourfourfour.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.service.impl.GenericServiceImpl;
import com.aladdinworksfourfourfour.dao.DataCenterDAO;
import com.aladdinworksfourfourfour.domain.DataCenter;
import com.aladdinworksfourfourfour.dto.DataCenterDTO;
import com.aladdinworksfourfourfour.dto.DataCenterSearchDTO;
import com.aladdinworksfourfourfour.dto.DataCenterPageDTO;
import com.aladdinworksfourfourfour.dto.DataCenterConvertCriteriaDTO;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import com.aladdinworksfourfourfour.service.DataCenterService;
import com.aladdinworksfourfourfour.util.ControllerUtils;





@Service
public class DataCenterServiceImpl extends GenericServiceImpl<DataCenter, Integer> implements DataCenterService {

    private final static Logger logger = LoggerFactory.getLogger(DataCenterServiceImpl.class);

	@Autowired
	DataCenterDAO dataCenterDao;

	


	@Override
	public GenericDAO<DataCenter, Integer> getDAO() {
		return (GenericDAO<DataCenter, Integer>) dataCenterDao;
	}
	
	public List<DataCenter> findAll () {
		List<DataCenter> dataCenters = dataCenterDao.findAll();
		
		return dataCenters;	
		
	}

	public ResultDTO addDataCenter(DataCenterDTO dataCenterDTO, RequestDTO requestDTO) {

		DataCenter dataCenter = new DataCenter();

		dataCenter.setDataCenterId(dataCenterDTO.getDataCenterId());


		dataCenter.setName(dataCenterDTO.getName());


		dataCenter.setLocation(dataCenterDTO.getLocation());


		dataCenter.setCapacity(dataCenterDTO.getCapacity());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		dataCenter = dataCenterDao.save(dataCenter);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<DataCenter> getAllDataCenters(Pageable pageable) {
		return dataCenterDao.findAll(pageable);
	}

	public Page<DataCenter> getAllDataCenters(Specification<DataCenter> spec, Pageable pageable) {
		return dataCenterDao.findAll(spec, pageable);
	}

	public ResponseEntity<DataCenterPageDTO> getDataCenters(DataCenterSearchDTO dataCenterSearchDTO) {
	
			Integer dataCenterId = dataCenterSearchDTO.getDataCenterId(); 
 			String name = dataCenterSearchDTO.getName(); 
 			String location = dataCenterSearchDTO.getLocation(); 
  			String sortBy = dataCenterSearchDTO.getSortBy();
			String sortOrder = dataCenterSearchDTO.getSortOrder();
			String searchQuery = dataCenterSearchDTO.getSearchQuery();
			Integer page = dataCenterSearchDTO.getPage();
			Integer size = dataCenterSearchDTO.getSize();

	        Specification<DataCenter> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, dataCenterId, "dataCenterId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, location, "location"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("location")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<DataCenter> dataCenters = this.getAllDataCenters(spec, pageable);
		
		//System.out.println(String.valueOf(dataCenters.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(dataCenters.getTotalPages()));
		
		List<DataCenter> dataCentersList = dataCenters.getContent();
		
		DataCenterConvertCriteriaDTO convertCriteria = new DataCenterConvertCriteriaDTO();
		List<DataCenterDTO> dataCenterDTOs = this.convertDataCentersToDataCenterDTOs(dataCentersList,convertCriteria);
		
		DataCenterPageDTO dataCenterPageDTO = new DataCenterPageDTO();
		dataCenterPageDTO.setDataCenters(dataCenterDTOs);
		dataCenterPageDTO.setTotalElements(dataCenters.getTotalElements());
		return ResponseEntity.ok(dataCenterPageDTO);
	}

	public List<DataCenterDTO> convertDataCentersToDataCenterDTOs(List<DataCenter> dataCenters, DataCenterConvertCriteriaDTO convertCriteria) {
		
		List<DataCenterDTO> dataCenterDTOs = new ArrayList<DataCenterDTO>();
		
		for (DataCenter dataCenter : dataCenters) {
			dataCenterDTOs.add(convertDataCenterToDataCenterDTO(dataCenter,convertCriteria));
		}
		
		return dataCenterDTOs;

	}
	
	public DataCenterDTO convertDataCenterToDataCenterDTO(DataCenter dataCenter, DataCenterConvertCriteriaDTO convertCriteria) {
		
		DataCenterDTO dataCenterDTO = new DataCenterDTO();
		
		dataCenterDTO.setDataCenterId(dataCenter.getDataCenterId());

	
		dataCenterDTO.setName(dataCenter.getName());

	
		dataCenterDTO.setLocation(dataCenter.getLocation());

	
		dataCenterDTO.setCapacity(dataCenter.getCapacity());

	

		
		return dataCenterDTO;
	}

	public ResultDTO updateDataCenter(DataCenterDTO dataCenterDTO, RequestDTO requestDTO) {
		
		DataCenter dataCenter = dataCenterDao.getById(dataCenterDTO.getDataCenterId());

		dataCenter.setDataCenterId(ControllerUtils.setValue(dataCenter.getDataCenterId(), dataCenterDTO.getDataCenterId()));

		dataCenter.setName(ControllerUtils.setValue(dataCenter.getName(), dataCenterDTO.getName()));

		dataCenter.setLocation(ControllerUtils.setValue(dataCenter.getLocation(), dataCenterDTO.getLocation()));

		dataCenter.setCapacity(ControllerUtils.setValue(dataCenter.getCapacity(), dataCenterDTO.getCapacity()));



        dataCenter = dataCenterDao.save(dataCenter);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DataCenterDTO getDataCenterDTOById(Integer dataCenterId) {
	
		DataCenter dataCenter = dataCenterDao.getById(dataCenterId);
			
		
		DataCenterConvertCriteriaDTO convertCriteria = new DataCenterConvertCriteriaDTO();
		return(this.convertDataCenterToDataCenterDTO(dataCenter,convertCriteria));
	}







}
