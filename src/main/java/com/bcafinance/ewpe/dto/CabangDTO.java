package com.bcafinance.ewpe.dto;

import com.bcafinance.ewpe.model.Karyawan;
import com.bcafinance.ewpe.model.KelasCabang;
import com.bcafinance.ewpe.model.Regional;
import com.bcafinance.ewpe.model.Sales;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

public class CabangDTO {


    private Long kodeCabang;

    private String namaCabang;

    private String namaBM;

    private List<GetKaryawanDTO> listKaryawan;

    private GetRegionalDTO region;

    private List<GetSalesDTO> listSales;

    private GetKelasCabangDTO kelas;

    public Long getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(Long kodeCabang) {
        this.kodeCabang = kodeCabang;
    }

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }

    public String getNamaBM() {
        return namaBM;
    }

    public void setNamaBM(String namaBM) {
        this.namaBM = namaBM;
    }

    public List<GetKaryawanDTO> getListKaryawan() {
        return listKaryawan;
    }

    public void setListKaryawan(List<GetKaryawanDTO> listKaryawan) {
        this.listKaryawan = listKaryawan;
    }

    public GetRegionalDTO getRegion() {
        return region;
    }

    public void setRegion(GetRegionalDTO region) {
        this.region = region;
    }

    public List<GetSalesDTO> getListSales() {
        return listSales;
    }

    public void setListSales(List<GetSalesDTO> listSales) {
        this.listSales = listSales;
    }

    public GetKelasCabangDTO getKelas() {
        return kelas;
    }

    public void setKelas(GetKelasCabangDTO kelas) {
        this.kelas = kelas;
    }
}
