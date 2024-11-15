package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.Report;





public interface ReportDAO extends GenericDAO<Report, Integer> {
  
	List<Report> findAll();
	






}


