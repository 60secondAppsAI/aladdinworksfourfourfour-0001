package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.NetworkEquipment;





public interface NetworkEquipmentDAO extends GenericDAO<NetworkEquipment, Integer> {
  
	List<NetworkEquipment> findAll();
	






}


