package com.bcafinance.ewpe.dto;

import com.bcafinance.ewpe.model.Cabang;

import java.util.List;

public class GetRegionalDTO {


    private Long kodeRegion;
    private String namaRegion;

    private String namaRM;


    public Long getKodeRegion() {
        return kodeRegion;
    }

    public void setKodeRegion(Long kodeRegion) {
        this.kodeRegion = kodeRegion;
    }

    public String getNamaRegion() {
        return namaRegion;
    }

    public void setNamaRegion(String namaRegion) {
        this.namaRegion = namaRegion;
    }

    public String getNamaRM() {
        return namaRM;
    }

    public void setNamaRM(String namaRM) {
        this.namaRM = namaRM;
    }

}
