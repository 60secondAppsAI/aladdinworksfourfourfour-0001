package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.PowerStrip;





public interface PowerStripDAO extends GenericDAO<PowerStrip, Integer> {
  
	List<PowerStrip> findAll();
	






}


