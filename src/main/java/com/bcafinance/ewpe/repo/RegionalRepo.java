package com.bcafinance.ewpe.repo;

import com.bcafinance.ewpe.model.Regional;
import com.bcafinance.ewpe.model.Sales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionalRepo extends JpaRepository<Regional, Long> {
    public Page<Regional> findByNamaRegionContains(Pageable p, String val);

    public Page<Regional> findByNamaRMContains(Pageable p, String val);
}
