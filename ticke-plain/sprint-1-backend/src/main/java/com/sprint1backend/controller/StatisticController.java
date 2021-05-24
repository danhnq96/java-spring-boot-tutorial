package com.sprint1backend.controller;


import com.sprint1backend.entity.Report;
import com.sprint1backend.entity.Statistic;
import com.sprint1backend.service.statistic.StatisticServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class StatisticController {
    @Autowired
    private StatisticServices statisticServices;
    static List<Statistic> statisticList = new ArrayList<>();
    static List<Report> reportList = new ArrayList<>();

    @PostMapping("/report")
    public long createStatisticFromReport(@RequestBody Report requestReport) throws MalformedURLException {
        Statistic newStatistic = new Statistic();
        if (requestReport.getItemReport().equals("staff")){
            newStatistic = this.statisticServices.createFromReportItemEmployee(requestReport);
        }
        if (requestReport.getItemReport().equals("money")){
            newStatistic = this.statisticServices.createFromReportItemMoney(requestReport);
        }
        if (requestReport.getItemReport().equals("brand")){
            newStatistic = this.statisticServices.createFromReportItemBrand(requestReport);
        }
        if (requestReport.getItemReport().equals("")){
            newStatistic.setType("");
        }
        newStatistic.setId(statisticList.size()+1);
        statisticList.add(newStatistic);
        requestReport.setId(reportList.size()+1);
        reportList.add(requestReport);
        return newStatistic.getId();
    }

    @GetMapping("/report")
    public ResponseEntity<List<Report>> getListReport(){
        return new ResponseEntity<List<Report>>(reportList, HttpStatus.OK);
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<Statistic>> getListStatistic(){
        return new ResponseEntity<List<Statistic>>(statisticList, HttpStatus.OK);
    }
    @GetMapping("/statistic/{id}")
    public ResponseEntity<Statistic> getStatisticById(@PathVariable long id){
        return new ResponseEntity<Statistic>(statisticList.get((int)id-1), HttpStatus.OK);
    }
}
