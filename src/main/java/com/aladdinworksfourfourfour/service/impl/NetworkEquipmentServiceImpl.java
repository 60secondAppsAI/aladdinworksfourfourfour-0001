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
import com.aladdinworksfourfourfour.dao.NetworkEquipmentDAO;
import com.aladdinworksfourfourfour.domain.NetworkEquipment;
import com.aladdinworksfourfourfour.dto.NetworkEquipmentDTO;
import com.aladdinworksfourfourfour.dto.NetworkEquipmentSearchDTO;
import com.aladdinworksfourfourfour.dto.NetworkEquipmentPageDTO;
import com.aladdinworksfourfourfour.dto.NetworkEquipmentConvertCriteriaDTO;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import com.aladdinworksfourfourfour.service.NetworkEquipmentService;
import com.aladdinworksfourfourfour.util.ControllerUtils;





@Service
public class NetworkEquipmentServiceImpl extends GenericServiceImpl<NetworkEquipment, Integer> implements NetworkEquipmentService {

    private final static Logger logger = LoggerFactory.getLogger(NetworkEquipmentServiceImpl.class);

	@Autowired
	NetworkEquipmentDAO networkEquipmentDao;

	


	@Override
	public GenericDAO<NetworkEquipment, Integer> getDAO() {
		return (GenericDAO<NetworkEquipment, Integer>) networkEquipmentDao;
	}
	
	public List<NetworkEquipment> findAll () {
		List<NetworkEquipment> networkEquipments = networkEquipmentDao.findAll();
		
		return networkEquipments;	
		
	}

	public ResultDTO addNetworkEquipment(NetworkEquipmentDTO networkEquipmentDTO, RequestDTO requestDTO) {

		NetworkEquipment networkEquipment = new NetworkEquipment();

		networkEquipment.setNetworkEquipmentId(networkEquipmentDTO.getNetworkEquipmentId());


		networkEquipment.setModel(networkEquipmentDTO.getModel());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		networkEquipment = networkEquipmentDao.save(networkEquipment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<NetworkEquipment> getAllNetworkEquipments(Pageable pageable) {
		return networkEquipmentDao.findAll(pageable);
	}

	public Page<NetworkEquipment> getAllNetworkEquipments(Specification<NetworkEquipment> spec, Pageable pageable) {
		return networkEquipmentDao.findAll(spec, pageable);
	}

	public ResponseEntity<NetworkEquipmentPageDTO> getNetworkEquipments(NetworkEquipmentSearchDTO networkEquipmentSearchDTO) {
	
			Integer networkEquipmentId = networkEquipmentSearchDTO.getNetworkEquipmentId(); 
 			String model = networkEquipmentSearchDTO.getModel(); 
 			String sortBy = networkEquipmentSearchDTO.getSortBy();
			String sortOrder = networkEquipmentSearchDTO.getSortOrder();
			String searchQuery = networkEquipmentSearchDTO.getSearchQuery();
			Integer page = networkEquipmentSearchDTO.getPage();
			Integer size = networkEquipmentSearchDTO.getSize();

	        Specification<NetworkEquipment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, networkEquipmentId, "networkEquipmentId"); 
			
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

		Page<NetworkEquipment> networkEquipments = this.getAllNetworkEquipments(spec, pageable);
		
		//System.out.println(String.valueOf(networkEquipments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(networkEquipments.getTotalPages()));
		
		List<NetworkEquipment> networkEquipmentsList = networkEquipments.getContent();
		
		NetworkEquipmentConvertCriteriaDTO convertCriteria = new NetworkEquipmentConvertCriteriaDTO();
		List<NetworkEquipmentDTO> networkEquipmentDTOs = this.convertNetworkEquipmentsToNetworkEquipmentDTOs(networkEquipmentsList,convertCriteria);
		
		NetworkEquipmentPageDTO networkEquipmentPageDTO = new NetworkEquipmentPageDTO();
		networkEquipmentPageDTO.setNetworkEquipments(networkEquipmentDTOs);
		networkEquipmentPageDTO.setTotalElements(networkEquipments.getTotalElements());
		return ResponseEntity.ok(networkEquipmentPageDTO);
	}

	public List<NetworkEquipmentDTO> convertNetworkEquipmentsToNetworkEquipmentDTOs(List<NetworkEquipment> networkEquipments, NetworkEquipmentConvertCriteriaDTO convertCriteria) {
		
		List<NetworkEquipmentDTO> networkEquipmentDTOs = new ArrayList<NetworkEquipmentDTO>();
		
		for (NetworkEquipment networkEquipment : networkEquipments) {
			networkEquipmentDTOs.add(convertNetworkEquipmentToNetworkEquipmentDTO(networkEquipment,convertCriteria));
		}
		
		return networkEquipmentDTOs;

	}
	
	public NetworkEquipmentDTO convertNetworkEquipmentToNetworkEquipmentDTO(NetworkEquipment networkEquipment, NetworkEquipmentConvertCriteriaDTO convertCriteria) {
		
		NetworkEquipmentDTO networkEquipmentDTO = new NetworkEquipmentDTO();
		
		networkEquipmentDTO.setNetworkEquipmentId(networkEquipment.getNetworkEquipmentId());

	
		networkEquipmentDTO.setModel(networkEquipment.getModel());

	

		
		return networkEquipmentDTO;
	}

	public ResultDTO updateNetworkEquipment(NetworkEquipmentDTO networkEquipmentDTO, RequestDTO requestDTO) {
		
		NetworkEquipment networkEquipment = networkEquipmentDao.getById(networkEquipmentDTO.getNetworkEquipmentId());

		networkEquipment.setNetworkEquipmentId(ControllerUtils.setValue(networkEquipment.getNetworkEquipmentId(), networkEquipmentDTO.getNetworkEquipmentId()));

		networkEquipment.setModel(ControllerUtils.setValue(networkEquipment.getModel(), networkEquipmentDTO.getModel()));



        networkEquipment = networkEquipmentDao.save(networkEquipment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public NetworkEquipmentDTO getNetworkEquipmentDTOById(Integer networkEquipmentId) {
	
		NetworkEquipment networkEquipment = networkEquipmentDao.getById(networkEquipmentId);
			
		
		NetworkEquipmentConvertCriteriaDTO convertCriteria = new NetworkEquipmentConvertCriteriaDTO();
		return(this.convertNetworkEquipmentToNetworkEquipmentDTO(networkEquipment,convertCriteria));
	}







}
