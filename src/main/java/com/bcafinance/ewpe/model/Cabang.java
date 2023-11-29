package com.bcafinance.ewpe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MstCabang")
public class Cabang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KodeCabang")
    private Long kodeCabang;

    @Column(name = "NamaCabang")
    private String namaCabang;

    @Column(name = "NamaBM")
    private String namaBM;

    @OneToMany(mappedBy = "cabang")
//    @JsonBackReference
    private List<Karyawan> listKaryawan;

    @ManyToOne
    @JoinColumn(name = "IdRegional", foreignKey = @ForeignKey(name = "fkCabangToRegional"))
    private Regional region;

    @OneToMany(mappedBy = "cabang")
//    @JsonBackReference
    private List<Sales> listSales;

    @ManyToOne
    @JoinColumn(name = "IdKelas", foreignKey = @ForeignKey(name = "fkCabangToKelas"))
    private KelasCabang kelas;


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

    public List<Karyawan> getListKaryawan() {
        return listKaryawan;
    }

    public void setListKaryawan(List<Karyawan> listKaryawan) {
        this.listKaryawan = listKaryawan;
    }

    public Regional getRegion() {
        return region;
    }

    public void setRegion(Regional region) {
        this.region = region;
    }

    public List<Sales> getListSales() {
        return listSales;
    }

    public void setListSales(List<Sales> listSales) {
        this.listSales = listSales;
    }

    public KelasCabang getKelas() {
        return kelas;
    }

    public void setKelas(KelasCabang kelas) {
        this.kelas = kelas;
    }
}
