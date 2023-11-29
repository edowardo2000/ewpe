package com.bcafinance.ewpe.dto;

import com.bcafinance.ewpe.model.Karyawan;
import com.bcafinance.ewpe.model.KelasCabang;
import com.bcafinance.ewpe.model.Regional;
import com.bcafinance.ewpe.model.Sales;

import java.util.List;

public class GetCabangDTO {


    private Long kodeCabang;

    private String namaCabang;

    private String namaBM;


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

}
