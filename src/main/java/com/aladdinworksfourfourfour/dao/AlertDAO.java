package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.Alert;





public interface AlertDAO extends GenericDAO<Alert, Integer> {
  
	List<Alert> findAll();
	






}


