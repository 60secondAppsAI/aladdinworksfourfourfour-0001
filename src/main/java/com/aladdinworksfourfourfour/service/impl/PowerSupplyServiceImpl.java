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
import com.aladdinworksfourfourfour.dao.PowerSupplyDAO;
import com.aladdinworksfourfourfour.domain.PowerSupply;
import com.aladdinworksfourfourfour.dto.PowerSupplyDTO;
import com.aladdinworksfourfourfour.dto.PowerSupplySearchDTO;
import com.aladdinworksfourfourfour.dto.PowerSupplyPageDTO;
import com.aladdinworksfourfourfour.dto.PowerSupplyConvertCriteriaDTO;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import com.aladdinworksfourfourfour.service.PowerSupplyService;
import com.aladdinworksfourfourfour.util.ControllerUtils;





@Service
public class PowerSupplyServiceImpl extends GenericServiceImpl<PowerSupply, Integer> implements PowerSupplyService {

    private final static Logger logger = LoggerFactory.getLogger(PowerSupplyServiceImpl.class);

	@Autowired
	PowerSupplyDAO powerSupplyDao;

	


	@Override
	public GenericDAO<PowerSupply, Integer> getDAO() {
		return (GenericDAO<PowerSupply, Integer>) powerSupplyDao;
	}
	
	public List<PowerSupply> findAll () {
		List<PowerSupply> powerSupplys = powerSupplyDao.findAll();
		
		return powerSupplys;	
		
	}

	public ResultDTO addPowerSupply(PowerSupplyDTO powerSupplyDTO, RequestDTO requestDTO) {

		PowerSupply powerSupply = new PowerSupply();

		powerSupply.setPowerSupplyId(powerSupplyDTO.getPowerSupplyId());


		powerSupply.setModel(powerSupplyDTO.getModel());


		powerSupply.setOutput(powerSupplyDTO.getOutput());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		powerSupply = powerSupplyDao.save(powerSupply);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<PowerSupply> getAllPowerSupplys(Pageable pageable) {
		return powerSupplyDao.findAll(pageable);
	}

	public Page<PowerSupply> getAllPowerSupplys(Specification<PowerSupply> spec, Pageable pageable) {
		return powerSupplyDao.findAll(spec, pageable);
	}

	public ResponseEntity<PowerSupplyPageDTO> getPowerSupplys(PowerSupplySearchDTO powerSupplySearchDTO) {
	
			Integer powerSupplyId = powerSupplySearchDTO.getPowerSupplyId(); 
 			String model = powerSupplySearchDTO.getModel(); 
  			String sortBy = powerSupplySearchDTO.getSortBy();
			String sortOrder = powerSupplySearchDTO.getSortOrder();
			String searchQuery = powerSupplySearchDTO.getSearchQuery();
			Integer page = powerSupplySearchDTO.getPage();
			Integer size = powerSupplySearchDTO.getSize();

	        Specification<PowerSupply> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, powerSupplyId, "powerSupplyId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, model, "model"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("model")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<PowerSupply> powerSupplys = this.getAllPowerSupplys(spec, pageable);
		
		//System.out.println(String.valueOf(powerSupplys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(powerSupplys.getTotalPages()));
		
		List<PowerSupply> powerSupplysList = powerSupplys.getContent();
		
		PowerSupplyConvertCriteriaDTO convertCriteria = new PowerSupplyConvertCriteriaDTO();
		List<PowerSupplyDTO> powerSupplyDTOs = this.convertPowerSupplysToPowerSupplyDTOs(powerSupplysList,convertCriteria);
		
		PowerSupplyPageDTO powerSupplyPageDTO = new PowerSupplyPageDTO();
		powerSupplyPageDTO.setPowerSupplys(powerSupplyDTOs);
		powerSupplyPageDTO.setTotalElements(powerSupplys.getTotalElements());
		return ResponseEntity.ok(powerSupplyPageDTO);
	}

	public List<PowerSupplyDTO> convertPowerSupplysToPowerSupplyDTOs(List<PowerSupply> powerSupplys, PowerSupplyConvertCriteriaDTO convertCriteria) {
		
		List<PowerSupplyDTO> powerSupplyDTOs = new ArrayList<PowerSupplyDTO>();
		
		for (PowerSupply powerSupply : powerSupplys) {
			powerSupplyDTOs.add(convertPowerSupplyToPowerSupplyDTO(powerSupply,convertCriteria));
		}
		
		return powerSupplyDTOs;

	}
	
	public PowerSupplyDTO convertPowerSupplyToPowerSupplyDTO(PowerSupply powerSupply, PowerSupplyConvertCriteriaDTO convertCriteria) {
		
		PowerSupplyDTO powerSupplyDTO = new PowerSupplyDTO();
		
		powerSupplyDTO.setPowerSupplyId(powerSupply.getPowerSupplyId());

	
		powerSupplyDTO.setModel(powerSupply.getModel());

	
		powerSupplyDTO.setOutput(powerSupply.getOutput());

	

		
		return powerSupplyDTO;
	}

	public ResultDTO updatePowerSupply(PowerSupplyDTO powerSupplyDTO, RequestDTO requestDTO) {
		
		PowerSupply powerSupply = powerSupplyDao.getById(powerSupplyDTO.getPowerSupplyId());

		powerSupply.setPowerSupplyId(ControllerUtils.setValue(powerSupply.getPowerSupplyId(), powerSupplyDTO.getPowerSupplyId()));

		powerSupply.setModel(ControllerUtils.setValue(powerSupply.getModel(), powerSupplyDTO.getModel()));

		powerSupply.setOutput(ControllerUtils.setValue(powerSupply.getOutput(), powerSupplyDTO.getOutput()));



        powerSupply = powerSupplyDao.save(powerSupply);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PowerSupplyDTO getPowerSupplyDTOById(Integer powerSupplyId) {
	
		PowerSupply powerSupply = powerSupplyDao.getById(powerSupplyId);
			
		
		PowerSupplyConvertCriteriaDTO convertCriteria = new PowerSupplyConvertCriteriaDTO();
		return(this.convertPowerSupplyToPowerSupplyDTO(powerSupply,convertCriteria));
	}







}
