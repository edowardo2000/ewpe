package com.bcafinance.ewpe.repo;

import com.bcafinance.ewpe.model.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KaryawanRepo extends JpaRepository<Karyawan, Long> {
    public Page<Karyawan> findByNamaKaryawanContains(Pageable p, String val);

    public Optional<Karyawan> findByUserNameOrNip(String username, String nip);

    public Optional<Karyawan> findByUserName(String username);

}
