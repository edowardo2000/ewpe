package com.bcafinance.ewpe.repo;

import com.bcafinance.ewpe.model.KelasCabang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KelasCabangRepo extends JpaRepository<KelasCabang, Long> {
    public Page<KelasCabang> findByNamaKelasContains(Pageable p, String val);
}
