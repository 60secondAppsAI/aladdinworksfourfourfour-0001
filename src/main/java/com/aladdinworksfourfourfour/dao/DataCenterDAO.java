package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.DataCenter;





public interface DataCenterDAO extends GenericDAO<DataCenter, Integer> {
  
	List<DataCenter> findAll();
	






}


