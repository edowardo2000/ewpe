package com.bcafinance.ewpe.dto;

import com.bcafinance.ewpe.model.Karyawan;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

public class TargetDTO {
    private Long idTarget;

    private String unit;

    private String npl;

    private String ph;

    private GetKaryawanDTO karyawan;

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

    public GetKaryawanDTO getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(GetKaryawanDTO karyawan) {
        this.karyawan = karyawan;
    }
}
