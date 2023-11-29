package com.bcafinance.ewpe.repo;

import com.bcafinance.ewpe.model.Cabang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabangRepo extends JpaRepository<Cabang, Long> {
}
