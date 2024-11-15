package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.Rack;





public interface RackDAO extends GenericDAO<Rack, Integer> {
  
	List<Rack> findAll();
	






}


