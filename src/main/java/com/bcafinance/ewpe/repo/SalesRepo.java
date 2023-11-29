package com.bcafinance.ewpe.repo;

import com.bcafinance.ewpe.model.Sales;
import com.bcafinance.ewpe.model.Target;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalesRepo extends JpaRepository<Sales, Long> {
    public Optional<Sales> findByNpl(String npl);

    public Page<Sales> findByNplContains(Pageable p, String val);

    public Page<Sales> findByUnitContains(Pageable p, String val);

}
