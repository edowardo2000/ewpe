package com.bcafinance.ewpe.dto;

import com.bcafinance.ewpe.model.Cabang;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

public class KelasCabangDTO {

    private Long idKelas;

    private String namaKelas;

    private String target;

    private List<GetCabangDTO> listCabang;

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

    public List<GetCabangDTO> getListCabang() {
        return listCabang;
    }

    public void setListCabang(List<GetCabangDTO> listCabang) {
        this.listCabang = listCabang;
    }
}
