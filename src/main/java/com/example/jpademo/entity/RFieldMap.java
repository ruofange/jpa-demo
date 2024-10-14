package com.example.jpademo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "r_fieldmap", schema = "wfzp_eq")
public class RFieldMap {
    @Id
    @Column(name = "FieldId", nullable = false)
    private Integer id;

    @Column(name = "GroupType", nullable = false, length = 32)
    private String groupType;

    @Column(name = "EquipmentType", nullable = false, length = 1000)
    private String equipmentType;

    @Column(name = "FieldName", nullable = false, length = 1000)
    private String fieldName;

    @Column(name = "FieldChName", nullable = false, length = 1000)
    private String fieldChName;

    @Column(name = "FiledType", nullable = false, length = 50)
    private String filedType;

    @ColumnDefault("0")
    @Column(name = "IsRequired", nullable = false)
    private Boolean isRequired = false;

    @Column(name = "Regex", length = 1000)
    private String regex;

    @Column(name = "Remark", length = 1000)
    private String remark;

    @Column(name = "OrderNum")
    private Integer orderNum;

    @Column(name = "ValidateType")
    private Integer validateType;

    @Column(name = "EditTime", length = 50)
    private String editTime;

    @ColumnDefault("0")
    @Column(name = "IsSelect", nullable = false)
    private Boolean isSelect = false;

    @Column(name = "SelectContent", length = 1000)
    private String selectContent;

    @Column(name = "FilterType", length = 100)
    private String filterType;

    @ColumnDefault("0")
    @Column(name = "IsYjyd", nullable = false)
    private Boolean isYjyd = false;

    @ColumnDefault("0")
    @Column(name = "IsCondition", nullable = false)
    private Integer isCondition;

    @ColumnDefault("0")
    @Column(name = "IsUseful", nullable = false)
    private Integer isUseful;

    @ColumnDefault("1")
    @Column(name = "Status", nullable = false)
    private Integer status;

    @ColumnDefault("0")
    @Column(name = "IsPic")
    private Integer isPic;

    @Column(name = "EnableSort")
    private Integer enableSort;

    @Column(name = "SortType")
    private String sortType;

    @Column(name = "EnableMultiple")
    private Integer enableMultiple;

    @Column(name = "LinkId", length = 36)
    private String linkId;

    @Column(name = "SelectOrder")
    private Integer selectOrder;

    @Column(name = "GroupOrder")
    private Integer groupOrder;

}