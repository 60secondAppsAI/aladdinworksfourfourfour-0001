package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.Switch;





public interface SwitchDAO extends GenericDAO<Switch, Integer> {
  
	List<Switch> findAll();
	






}


