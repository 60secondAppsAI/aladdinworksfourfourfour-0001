package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.CoolingUnit;





public interface CoolingUnitDAO extends GenericDAO<CoolingUnit, Integer> {
  
	List<CoolingUnit> findAll();
	






}


