package com.example.jpademo.entity;

import jakarta.persistence.*;
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