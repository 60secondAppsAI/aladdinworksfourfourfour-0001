package com.aladdinworksfourfourfour.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworksfourfourfour.domain.Generator;
import com.aladdinworksfourfourfour.dto.GeneratorDTO;
import com.aladdinworksfourfourfour.dto.GeneratorSearchDTO;
import com.aladdinworksfourfourfour.dto.GeneratorPageDTO;
import com.aladdinworksfourfourfour.dto.GeneratorConvertCriteriaDTO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface GeneratorService extends GenericService<Generator, Integer> {

	List<Generator> findAll();

	ResultDTO addGenerator(GeneratorDTO generatorDTO, RequestDTO requestDTO);

	ResultDTO updateGenerator(GeneratorDTO generatorDTO, RequestDTO requestDTO);

    Page<Generator> getAllGenerators(Pageable pageable);

    Page<Generator> getAllGenerators(Specification<Generator> spec, Pageable pageable);

	ResponseEntity<GeneratorPageDTO> getGenerators(GeneratorSearchDTO generatorSearchDTO);
	
	List<GeneratorDTO> convertGeneratorsToGeneratorDTOs(List<Generator> generators, GeneratorConvertCriteriaDTO convertCriteria);

	GeneratorDTO getGeneratorDTOById(Integer generatorId);







}





