package com.bcafinance.ewpe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Regional")
public class Regional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KodeRegion")
    private Long kodeRegion;

    @Column(name = "NamaRegion")
    private String namaRegion;

    @Column(name = "NamaRM")
    private String namaRM;

    @OneToMany(mappedBy = "region")
//    @JsonBackReference
    private List<Cabang> listCabang;

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

    public List<Cabang> getListCabang() {
        return listCabang;
    }

    public void setListCabang(List<Cabang> listCabang) {
        this.listCabang = listCabang;
    }
}
