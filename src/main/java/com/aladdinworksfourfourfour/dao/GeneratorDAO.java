package com.aladdinworksfourfourfour.dao;

import java.util.List;

import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.domain.Generator;





public interface GeneratorDAO extends GenericDAO<Generator, Integer> {
  
	List<Generator> findAll();
	






}


