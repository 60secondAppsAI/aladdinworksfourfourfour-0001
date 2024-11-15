package com.aladdinworksfourfourfour.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworksfourfourfour.domain.Issue;
import com.aladdinworksfourfourfour.dto.IssueDTO;
import com.aladdinworksfourfourfour.dto.IssueSearchDTO;
import com.aladdinworksfourfourfour.dto.IssuePageDTO;
import com.aladdinworksfourfourfour.dto.IssueConvertCriteriaDTO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface IssueService extends GenericService<Issue, Integer> {

	List<Issue> findAll();

	ResultDTO addIssue(IssueDTO issueDTO, RequestDTO requestDTO);

	ResultDTO updateIssue(IssueDTO issueDTO, RequestDTO requestDTO);

    Page<Issue> getAllIssues(Pageable pageable);

    Page<Issue> getAllIssues(Specification<Issue> spec, Pageable pageable);

	ResponseEntity<IssuePageDTO> getIssues(IssueSearchDTO issueSearchDTO);
	
	List<IssueDTO> convertIssuesToIssueDTOs(List<Issue> issues, IssueConvertCriteriaDTO convertCriteria);

	IssueDTO getIssueDTOById(Integer issueId);







}





