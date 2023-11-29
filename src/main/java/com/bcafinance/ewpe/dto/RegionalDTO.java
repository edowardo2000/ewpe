package com.bcafinance.ewpe.dto;

import com.bcafinance.ewpe.model.Cabang;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

public class RegionalDTO {


    private Long kodeRegion;
    private String namaRegion;

    private String namaRM;

    private List<GetCabangDTO> listCabang;

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

    public List<GetCabangDTO> getListCabang() {
        return listCabang;
    }

    public void setListCabang(List<GetCabangDTO> listCabang) {
        this.listCabang = listCabang;
    }
}
