package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.PowerSupply;





public interface PowerSupplyDAO extends GenericDAO<PowerSupply, Integer> {
  
	List<PowerSupply> findAll();
	






}


