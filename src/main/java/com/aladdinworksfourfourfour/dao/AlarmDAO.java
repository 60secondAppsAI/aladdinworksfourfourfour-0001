package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.Alarm;





public interface AlarmDAO extends GenericDAO<Alarm, Integer> {
  
	List<Alarm> findAll();
	






}


