package com.example.jpademo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tag", schema = "wfzp_eq")
public class Tag {
    @EmbeddedId
    private TagId id;

    @Column(name = "TagValue")
    private String tagValue;

}