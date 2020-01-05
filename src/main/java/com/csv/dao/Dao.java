package com.csv.dao;

import com.csv.entity.CSVEntity;

import java.util.List;

public interface Dao {
    List<CSVEntity> storeCsv(List<CSVEntity> csvEntities);
    List<CSVEntity> getAlldata();
    CSVEntity getDataById(String id);
}
