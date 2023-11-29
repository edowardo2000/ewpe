package com.bcafinance.ewpe.dto;

import com.bcafinance.ewpe.model.Karyawan;

public class GetTargetDTO {
    private Long idTarget;

    private String unit;

    private String npl;

    private String ph;


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

}
