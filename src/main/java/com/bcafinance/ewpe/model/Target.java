package com.bcafinance.ewpe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Target")
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTarget")
    private Long idTarget;

    @Column(name = "Unit")
    private String unit;

    @Column(name = "Npl")
    private String npl;

    @Column(name = "Ph")
    private String ph;

    @OneToOne(mappedBy = "target")
//    @JsonBackReference
    private Karyawan karyawan;

    public Long getIdTarget() {
        return idTarget;
    }

    public void setIdTarget(Long idTarget) {
        this.idTarget = idTarget;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNpl() {
        return npl;
    }

    public void setNpl(String npl) {
        this.npl = npl;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }
}
