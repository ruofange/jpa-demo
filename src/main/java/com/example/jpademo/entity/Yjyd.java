package com.example.jpademo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "yjyd")
public class Yjyd {
    @Id
    @Column(name = "SBBM", nullable = false, length = 36)
    private String sbbm;

    @Lob
    @Column(name = "SBMC")
    private String sbmc;

    @Lob
    @Column(name = "LWSX")
    private String lwsx;

    @Lob
    @Column(name = "XZQY")
    private String xzqy;

    @Lob
    @Column(name = "SBZT")
    private String sbzt;

    @Lob
    @Column(name = "SXJLX")
    private String sxjlx;

    @Lob
    @Column(name = "SFJRSJPT")
    private String sfjrsjpt;

    @Lob
    @Column(name = "SSXQGAJG")
    private String ssxqgajg;

    @Lob
    @Column(name = "JD")
    private String jd;

    @Lob
    @Column(name = "WD")
    private String wd;

    @Lob
    @Column(name = "JPBH")
    private String jpbh;

    @Lob
    @Column(name = "DYCCSBIP")
    private String dyccsbip;

    @Lob
    @Column(name = "DYCCSBTD")
    private String dyccsbtd;

    @Lob
    @Column(name = "IPV4")
    private String ipv4;

    @Lob
    @Column(name = "SPXHLX")
    private String spxhlx;

    @Lob
    @Column(name = "DWSC")
    private String dwsc;

    @Lob
    @Column(name = "SBCS")
    private String sbcs;

    @Lob
    @Column(name = "JKDWLX")
    private String jkdwlx;

    @Lob
    @Column(name = "JSYYLB")
    private String jsyylb;

    @Lob
    @Column(name = "IPV6")
    private String ipv6;

    @Lob
    @Column(name = "MACDZ")
    private String macdz;

    @Lob
    @Column(name = "SFDWGX")
    private String sfdwgx;

    @Lob
    @Column(name = "AZFS")
    private String azfs;

    @Lob
    @Column(name = "AZGD")
    private String azgd;

    @Lob
    @Column(name = "AZSJ")
    private String azsj;

    @Lob
    @Column(name = "GLDW")
    private String gldw;

    @Lob
    @Column(name = "GLDWLXFS")
    private String gldwlxfs;

    @Lob
    @Column(name = "JSFW")
    private String jsfw;

    @Lob
    @Column(name = "KSJL")
    private String ksjl;

    @Lob
    @Column(name = "LXBCTS")
    private String lxbcts;

    @Lob
    @Column(name = "BGSX")
    private String bgsx;

    @Lob
    @Column(name = "SBXH")
    private String sbxh;

    @Lob
    @Column(name = "SPFBL")
    private String spfbl;

    @Column(name = "SXJGNLX", length = 32)
    private String sxjgnlx;

    @Lob
    @Column(name = "SXJBMGS")
    private String sxjbmgs;

    @Lob
    @Column(name = "AZDZ")
    private String azdz;

    @Lob
    @Column(name = "SSBMHY")
    private String ssbmhy;

    @Lob
    @Column(name = "SXJWZLX")
    private String sxjwzlx;

    @Lob
    @Column(name = "SFYBD")
    private String sfybd;

    @Lob
    @Column(name = "isZbLegal")
    private String isZbLegal;

    @Lob
    @Column(name = "CSBH")
    private String csbh;

    @Lob
    @Column(name = "CSMC")
    private String csmc;

    @Lob
    @Column(name = "BWLX")
    private String bwlx;

    @Lob
    @Column(name = "CRFX")
    private String crfx;

}