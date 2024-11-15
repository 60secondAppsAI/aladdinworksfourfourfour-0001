package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.Issue;





public interface IssueDAO extends GenericDAO<Issue, Integer> {
  
	List<Issue> findAll();
	






}


