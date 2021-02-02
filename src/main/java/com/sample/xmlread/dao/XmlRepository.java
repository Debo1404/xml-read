package com.sample.xmlread.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.xmlread.model.IPsubnet;

@Repository
public interface XmlRepository extends CrudRepository<IPsubnet, Integer>{

}
