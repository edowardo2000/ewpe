package com.bcafinance.ewpe.dto;

import com.bcafinance.ewpe.model.Cabang;
import com.bcafinance.ewpe.model.Sales;
import com.bcafinance.ewpe.model.Target;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

public class KaryawanDTO {

    private Long idKaryawan;

    private String namaKaryawan;

    private String password;

    private String jabatan;

    private String nip;

    private Date tglMasuk;

    private GetCabangDTO cabang;

    private GetSalesDTO sales;

    private GetTargetDTO target;

    public Long getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(Long idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Date getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(Date tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public GetCabangDTO getCabang() {
        return cabang;
    }

    public void setCabang(GetCabangDTO cabang) {
        this.cabang = cabang;
    }

    public GetSalesDTO getSales() {
        return sales;
    }

    public void setSales(GetSalesDTO sales) {
        this.sales = sales;
    }

    public GetTargetDTO getTarget() {
        return target;
    }

    public void setTarget(GetTargetDTO target) {
        this.target = target;
    }
}
