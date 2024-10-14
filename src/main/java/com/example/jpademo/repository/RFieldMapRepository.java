package com.example.jpademo.repository;

import com.example.jpademo.entity.RFieldMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RFieldMapRepository extends JpaRepository<RFieldMap, Integer>, JpaSpecificationExecutor<RFieldMap> {
}
