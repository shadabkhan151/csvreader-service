package com.demo.dao;

import com.demo.entity.CSVEntity;
import com.demo.repository.CSVRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoImpl implements Dao {

    /** variable to hold logger object */
    private static final Logger LOGGER = LoggerFactory.getLogger(DaoImpl.class);

    @Autowired
    private CSVRepository csvRepository;

    @Override
    public List<CSVEntity> storeCsv(List<CSVEntity> csvEntities){
        return csvRepository.saveAll(csvEntities);
    }

    @Override
    public List<CSVEntity> getAlldata(){
        return csvRepository.getAll();
    }

    @Override
    public CSVEntity getDataById(String id){
        return csvRepository.getById(id);
    }
}