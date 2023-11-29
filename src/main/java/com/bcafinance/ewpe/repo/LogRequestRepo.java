package com.bcafinance.ewpe.repo;

import com.bcafinance.ewpe.model.LogRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface LogRequestRepo extends CrudRepository<LogRequest,Long> {
    
}