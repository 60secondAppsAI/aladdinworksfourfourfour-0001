package com.aladdinworksfourfourfour.service;

import com.aladdinworksfourfourfour.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}