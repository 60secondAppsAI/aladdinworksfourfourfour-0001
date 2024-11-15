package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.Maintenance;





public interface MaintenanceDAO extends GenericDAO<Maintenance, Integer> {
  
	List<Maintenance> findAll();
	






}


