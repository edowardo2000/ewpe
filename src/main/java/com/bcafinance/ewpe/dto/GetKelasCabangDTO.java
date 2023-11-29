package com.bcafinance.ewpe.dto;

import com.bcafinance.ewpe.model.Cabang;

import java.util.List;

public class GetKelasCabangDTO {

    private Long idKelas;

    private String namaKelas;

    private String target;


    public Long getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(Long idKelas) {
        this.idKelas = idKelas;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

}
