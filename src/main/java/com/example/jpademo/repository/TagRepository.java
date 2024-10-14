package com.example.jpademo.repository;

import com.example.jpademo.entity.Tag;
import com.example.jpademo.entity.TagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagRepository extends JpaRepository<Tag, TagId> , JpaSpecificationExecutor<Tag> {
}
