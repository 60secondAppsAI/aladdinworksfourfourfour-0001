package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.Notification;





public interface NotificationDAO extends GenericDAO<Notification, Integer> {
  
	List<Notification> findAll();
	






}


