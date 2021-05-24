package com.sprint1backend.service.statistic.impl;

import com.sprint1backend.entity.Report;
import com.sprint1backend.repository.ReportRepository;
import com.sprint1backend.service.statistic.ReportServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServicesImpl implements ReportServices {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public void saveReport(Report report) {
        this.reportRepository.save(report);
    }

}
