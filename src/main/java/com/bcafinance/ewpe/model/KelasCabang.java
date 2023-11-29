package com.bcafinance.ewpe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "KelasCabang")
public class KelasCabang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKelas")
    private Long idKelas;

    @Column(name = "NamaKelas")
    private String namaKelas;

    @Column(name = "Target")
    private String target;

    @OneToMany(mappedBy = "kelas")
//    @JsonBackReference
    private List<Cabang> listCabang;

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

    public List<Cabang> getListCabang() {
        return listCabang;
    }

    public void setListCabang(List<Cabang> listCabang) {
        this.listCabang = listCabang;
    }
}
