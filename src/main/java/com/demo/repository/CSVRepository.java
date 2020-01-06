package com.demo.repository;

import com.demo.entity.CSVEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CSVRepository extends JpaRepository<CSVEntity, String> {
    @Query("SELECT c FROM CSVEntity c")
    List<CSVEntity> getAll();

    @Query("SELECT c FROM CSVEntity c where c.id =:id")
    CSVEntity getById(@Param("id") String id);
}