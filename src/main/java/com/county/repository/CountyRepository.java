package com.county.repository;

import com.county.entity.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface CountyRepository extends JpaRepository<County, String> {
    @Query("SELECT c FROM County c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%',:frag,'%')) ORDER BY c.state,c.name")
    List<County> findByNameFragment(@Param("frag") String frag);

    @Query("SELECT c FROM County c WHERE LOWER(c.state) LIKE LOWER(CONCAT('%',:frag,'%')) ORDER BY c.state,c.name")
    List<County> findByStateFragment(@Param("frag") String frag);

    @Query("SELECT c FROM County c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%',:n,'%')) AND LOWER(c.state) LIKE LOWER(CONCAT('%',:s,'%')) ORDER BY c.state,c.name")
    List<County> findByNameAndStateFragments(@Param("n") String n, @Param("s") String s);
}