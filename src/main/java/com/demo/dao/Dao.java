package com.demo.dao;

import com.demo.entity.CSVEntity;

import java.util.List;

public interface Dao {
    List<CSVEntity> storeCsv(List<CSVEntity> csvEntities);
    List<CSVEntity> getAlldata();
    CSVEntity getDataById(String id);
}
