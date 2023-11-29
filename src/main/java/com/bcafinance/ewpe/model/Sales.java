package com.bcafinance.ewpe.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MstSales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSales")
    private Long idSales;

    @Column(name = "Npl")
    private String npl;

    @Column(name = "Unit")
    private String unit;

    @Column (name="Bulan")
    private Date bulan;

    @ManyToOne
    @JoinColumn(name = "IdKaryawan", foreignKey = @ForeignKey(name = "fkSales2ToKaryawan2"))
    private Karyawan karyawan;

    @ManyToOne
    @JoinColumn(name = "kodeCabang", foreignKey = @ForeignKey(name = "fkSalesToCabang"))
    private Cabang cabang;

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

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public Cabang getCabang() {
        return cabang;
    }

    public void setCabang(Cabang cabang) {
        this.cabang = cabang;
    }

    public Date getBulan() {
        return bulan;
    }

    public void setBulan(Date bulan) {
        this.bulan = bulan;
    }


}
