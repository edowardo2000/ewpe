package com.bcafinance.ewpe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jdk.jfr.Unsigned;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MstKaryawan")
public class Karyawan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKaryawan")
    private Long idKaryawan;

    @Column(name = "NamaKaryawan")
    private String namaKaryawan;

    @Column(name = "Password")
    private String password;

    @Column(name = "Jabatan")
    private String jabatan;

    @Column(name="UserName")
    private String userName;

    @Column(name = "Nip")
    private String nip;

    @Column(name = "TglMasuk")
    private Date tglMasuk;

    @Column(name= "Token")
    private String token;

    @ManyToOne
    @JoinColumn(name = "KodeCabang", foreignKey = @ForeignKey(name = "fkKaryawanToCabang"))
    private Cabang cabang;

    @OneToMany(mappedBy = "karyawan")
//    @JsonBackReference
    private List<Sales> sales;

    @OneToOne
    @JoinColumn(name = "IdTarget", foreignKey = @ForeignKey(name = "fkKaryawanToTarget"))
    private Target target;

    public Long getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(Long idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Cabang getCabang() {
        return cabang;
    }

    public void setCabang(Cabang cabang) {
        this.cabang = cabang;
    }

    public List<Sales> getSales() {
        return sales;
    }

    public void setSales(List<Sales> sales) {
        this.sales = sales;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
