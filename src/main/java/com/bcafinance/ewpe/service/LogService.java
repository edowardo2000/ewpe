package com.bcafinance.ewpe.service;

import com.bcafinance.ewpe.model.LogRequest;
import com.bcafinance.ewpe.repo.LogRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogService {

    private LogRequestRepo logRequestRepo;

    @Autowired
    public LogService(LogRequestRepo logRequestRepo) {
        this.logRequestRepo = logRequestRepo;
    }

    public void saveLog(LogRequest logRequest)
    {
        logRequestRepo.save(logRequest);
    }
}