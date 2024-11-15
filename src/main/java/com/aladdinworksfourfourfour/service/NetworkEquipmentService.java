package com.aladdinworksfourfourfour.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworksfourfourfour.domain.NetworkEquipment;
import com.aladdinworksfourfourfour.dto.NetworkEquipmentDTO;
import com.aladdinworksfourfourfour.dto.NetworkEquipmentSearchDTO;
import com.aladdinworksfourfourfour.dto.NetworkEquipmentPageDTO;
import com.aladdinworksfourfourfour.dto.NetworkEquipmentConvertCriteriaDTO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface NetworkEquipmentService extends GenericService<NetworkEquipment, Integer> {

	List<NetworkEquipment> findAll();

	ResultDTO addNetworkEquipment(NetworkEquipmentDTO networkEquipmentDTO, RequestDTO requestDTO);

	ResultDTO updateNetworkEquipment(NetworkEquipmentDTO networkEquipmentDTO, RequestDTO requestDTO);

    Page<NetworkEquipment> getAllNetworkEquipments(Pageable pageable);

    Page<NetworkEquipment> getAllNetworkEquipments(Specification<NetworkEquipment> spec, Pageable pageable);

	ResponseEntity<NetworkEquipmentPageDTO> getNetworkEquipments(NetworkEquipmentSearchDTO networkEquipmentSearchDTO);
	
	List<NetworkEquipmentDTO> convertNetworkEquipmentsToNetworkEquipmentDTOs(List<NetworkEquipment> networkEquipments, NetworkEquipmentConvertCriteriaDTO convertCriteria);

	NetworkEquipmentDTO getNetworkEquipmentDTOById(Integer networkEquipmentId);







}





