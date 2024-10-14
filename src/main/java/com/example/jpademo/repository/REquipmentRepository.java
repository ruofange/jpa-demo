package com.example.jpademo.repository;

import com.example.jpademo.entity.REquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface REquipmentRepository extends JpaRepository<REquipment,String>, JpaSpecificationExecutor<REquipment> {
}
