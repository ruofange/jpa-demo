package com.example.jpademo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "r_equipment", schema = "wfzp_eq")
public class REquipment {
    @Id
    @Column(name = "EquipmentID", nullable = false, length = 32)
    private String equipmentID;

    @Column(name = "AreaID", length = 32)
    private String areaID;

    @Column(name = "Name", length = 100)
    private String name;

    @Column(name = "Spell", length = 100)
    private String spell;

    @Column(name = "Code", length = 100)
    private String code;

    @Column(name = "MainType", length = 30)
    private String mainType;

    @Column(name = "SubType", length = 30)
    private String subType;

    @Column(name = "Brand", length = 50)
    private String brand;

    @Column(name = "Model", length = 30)
    private String model;

    @Column(name = "IPAddress", length = 100)
    private String iPAddress;

    @Column(name = "Port", length = 20)
    private String port;

    @Column(name = "LoginName", length = 100)
    private String loginName;

    @Column(name = "Password", length = 30)
    private String password;

    @Column(name = "IsYJYD", length = 10)
    private String isYJYD;

    @Column(name = "Remark", length = 200)
    private String remark;

    @Column(name = "UpdateTime", length = 254)
    private String updateTime;

    @Column(name = "AuditStatus")
    private Integer auditStatus;

    /*@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "ownerId",
            referencedColumnName = "equipmentId",
            insertable = false,
            updatable = false,
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )*/
    @Transient
    private List<Tag> tagList;

}