package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.Task;





public interface TaskDAO extends GenericDAO<Task, Integer> {
  
	List<Task> findAll();
	






}


