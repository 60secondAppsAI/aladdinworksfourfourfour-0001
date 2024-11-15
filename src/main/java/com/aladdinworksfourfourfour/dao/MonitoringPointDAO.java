package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.MonitoringPoint;





public interface MonitoringPointDAO extends GenericDAO<MonitoringPoint, Integer> {
  
	List<MonitoringPoint> findAll();
	






}


