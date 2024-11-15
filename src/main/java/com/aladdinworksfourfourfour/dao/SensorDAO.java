package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.Sensor;





public interface SensorDAO extends GenericDAO<Sensor, Integer> {
  
	List<Sensor> findAll();
	






}


