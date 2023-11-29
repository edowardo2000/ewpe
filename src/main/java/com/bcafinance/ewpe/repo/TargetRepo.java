package com.bcafinance.ewpe.repo;

import com.bcafinance.ewpe.model.Target;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetRepo extends JpaRepository<Target, Long> {
    public Page<Target> findByUnitContains(Pageable p, String val);

    public Page<Target> findByPhContains(Pageable p, String val);

    public Page<Target> findByNplContains(Pageable p, String val);
}
