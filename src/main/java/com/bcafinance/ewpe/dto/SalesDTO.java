package com.bcafinance.ewpe.dto;

import com.bcafinance.ewpe.model.Cabang;
import com.bcafinance.ewpe.model.Karyawan;

import javax.persistence.*;

public class SalesDTO {

    private Long idSales;

    private String npl;

    private String unit;

    private GetKaryawanDTO karyawan;

    private GetCabangDTO cabang;

    public Long getIdSales() {
        return idSales;
    }

    public void setIdSales(Long idSales) {
        this.idSales = idSales;
    }

    public String getNpl() {
        return npl;
    }

    public void setNpl(String npl) {
        this.npl = npl;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public GetKaryawanDTO getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(GetKaryawanDTO karyawan) {
        this.karyawan = karyawan;
    }

    public GetCabangDTO getCabang() {
        return cabang;
    }

    public void setCabang(GetCabangDTO cabang) {
        this.cabang = cabang;
    }
}
