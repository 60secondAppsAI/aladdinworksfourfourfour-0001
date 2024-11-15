package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.CurrentSensor;





public interface CurrentSensorDAO extends GenericDAO<CurrentSensor, Integer> {
  
	List<CurrentSensor> findAll();
	






}


