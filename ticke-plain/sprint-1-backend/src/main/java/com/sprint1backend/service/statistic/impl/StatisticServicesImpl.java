package com.sprint1backend.service.statistic.impl;


import com.sprint1backend.entity.Report;
import com.sprint1backend.entity.Statistic;
import com.sprint1backend.repository.StatisticRepository;
import com.sprint1backend.service.statistic.StatisticServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class StatisticServicesImpl implements StatisticServices {
    @Autowired
    private StatisticRepository statisticRepository;


    @Override
    public long getNumberEmployee() {
        return this.statisticRepository.getNumberEmployee();
    }

    @Override
    public long getPriceTicketOnDateEmployee(Date date) {
        return this.statisticRepository.getPriceTicketOnDateEmployee(date);
    }

    @Override
    public long getNumberTicketSoldOnDateEmployee(Date date) {
        return this.statisticRepository.getNumberTicketSoldOnDateEmployee(date);
    }

    @Override
    public long getTotalMoney() {
        return this.statisticRepository.getTotalMoney();
    }

    @Override
    public long getTotalTicketOfMoney(Date dateStart, Date dateEnd) {
        return this.statisticRepository.getTotalTicket(dateStart,dateEnd);
    }

    @Override
    public long getTotalTicketOnDateMoney(Date date) {
        return this.statisticRepository.getTotalMoneyOnDate(date);
    }

    @Override
    public long getTotalOfBrand(Date dateStart, Date dateEnd) {
        return this.statisticRepository.getTotalFlight(dateStart, dateEnd);
    }

    @Override
    public long getTotalTicketOnDateBrand(Date date) {
        return this.statisticRepository.getTotalPriceFlightOnDate(date);
    }

    @Override
    public long getNumberFlightOfBrand(Date date) {
        return this.statisticRepository.getNumberFlightOnDate(date);
    }

    @Override
    public Statistic createFromReportItemEmployee(Report reportItemEmployee) {
        Statistic newStatistic = new Statistic();
        try{
            if (this.checkDateReport(reportItemEmployee)) {
                newStatistic.setItem(reportItemEmployee.getItemReport());
                newStatistic.setType(reportItemEmployee.getTypeReport());
                newStatistic.setTimeSelectionCompare(reportItemEmployee.getCompareReportTimeSelection());
                newStatistic.setNumberItem(this.getNumberEmployee());
                newStatistic.setDateStart(reportItemEmployee.getDateStart());
                newStatistic.setDateEnd(reportItemEmployee.getDateEnd());
                newStatistic.setCompareReportDateStart(reportItemEmployee.getCompareReportDateStart());
                newStatistic.setCompareReportDateEnd(reportItemEmployee.getCompareReportDateEnd());
                newStatistic.setNumberTicketSellDateStart(this.getNumberTicketSoldOnDateEmployee(convertToDateSql(reportItemEmployee.getDateStart())));
                newStatistic.setNumberTicketSellDateEnd(this.getNumberTicketSoldOnDateEmployee(convertToDateSql(reportItemEmployee.getDateEnd())));
                newStatistic.setPriceTicketSellDateStart(this.getPriceTicketOnDateEmployee(convertToDateSql(reportItemEmployee.getDateStart())));
                newStatistic.setPriceTicketSellDateEnd(this.getPriceTicketOnDateEmployee(convertToDateSql(reportItemEmployee.getDateEnd())));
            } else {
                newStatistic.setType("");
            }
            if (!reportItemEmployee.getCompareReportTimeSelection().equals("")){
                if (this.checkCompareReport(reportItemEmployee)) {
                    newStatistic.setNumberTicketSellDateStartCompare(this.getNumberTicketSoldOnDateEmployee(convertToDateSql(reportItemEmployee.getCompareReportDateStart())));
                    newStatistic.setNumberTicketSellDateEndCompare(this.getNumberTicketSoldOnDateEmployee(convertToDateSql(reportItemEmployee.getCompareReportDateEnd())));
                    newStatistic.setPriceTicketSellDateStartCompare(this.getPriceTicketOnDateEmployee(convertToDateSql(reportItemEmployee.getCompareReportDateStart())));
                    newStatistic.setPriceTicketSellDateEndCompare(this.getPriceTicketOnDateEmployee(convertToDateSql(reportItemEmployee.getCompareReportDateEnd())));
                } else {
                    newStatistic.setType("");
                }
            }
            newStatistic.setMessage("ok");
            System.out.println(newStatistic.toString());
        } catch (Exception e){
            newStatistic.setMessage("error");
            System.out.println(newStatistic.toString());
        }

        return newStatistic;
    }

    private Date convertToDateSql(java.util.Date date){
        return new Date(date.getTime());
    }
    private long distanceDate(java.util.Date date1, java.util.Date date2){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        long distanceDay = (cal1.getTime().getTime() - cal2.getTime().getTime()) / (24 * 3600 * 1000);
        return distanceDay;
    }
    private boolean checkDateReport(Report report){
        if (
                (distanceDate(report.getDateEnd(),report.getDateStart()) <=7 && report.getTimeSelection().equals("week")) ||
                        (distanceDate(report.getDateEnd(),report.getDateStart()) <=30 && report.getTimeSelection().equals("month")) ||
                        (distanceDate(report.getDateEnd(),report.getDateStart()) <=90 && report.getTimeSelection().equals("quarter")) ||
                        (distanceDate(report.getDateEnd(),report.getDateStart()) <=365 && report.getTimeSelection().equals("year"))
        ) {
            return true;
        }
        return false;
    }
    private boolean checkCompareReport(Report report){
        if ((distanceDate(report.getCompareReportDateEnd(),report.getCompareReportDateStart()) <=7 && report.getCompareReportTimeSelection().equals("week")) ||
                (distanceDate(report.getCompareReportDateEnd(),report.getCompareReportDateStart()) <=30 && report.getCompareReportTimeSelection().equals("month")) ||
                (distanceDate(report.getCompareReportDateEnd(),report.getCompareReportDateStart()) <=90 && report.getCompareReportTimeSelection().equals("quarter")) ||
                (distanceDate(report.getCompareReportDateEnd(),report.getCompareReportDateStart()) <=365 && report.getCompareReportTimeSelection().equals("year"))
        ) {
            return true;
        }
        return false;
    }

    @Override
    public Statistic createFromReportItemMoney(Report reportItemMoney) {
        Statistic newStatistic = new Statistic();
        try{
            if (this.checkDateReport(reportItemMoney)) {
                newStatistic.setItem(reportItemMoney.getItemReport());
                newStatistic.setType(reportItemMoney.getTypeReport());
                newStatistic.setTimeSelectionCompare(reportItemMoney.getCompareReportTimeSelection());
                newStatistic.setNumberItem(this.getTotalMoney());
                newStatistic.setDateStart(reportItemMoney.getDateStart());
                newStatistic.setDateEnd(reportItemMoney.getDateEnd());
                newStatistic.setCompareReportDateStart(reportItemMoney.getCompareReportDateStart());
                newStatistic.setCompareReportDateEnd(reportItemMoney.getCompareReportDateEnd());
                newStatistic.setNumberTicketSellDateStart(this.getTotalTicketOfMoney(convertToDateSql(reportItemMoney.getDateStart()), convertToDateSql(reportItemMoney.getDateEnd())));
                newStatistic.setPriceTicketSellDateStart(this.getTotalTicketOnDateMoney(convertToDateSql(reportItemMoney.getDateStart())));
                newStatistic.setPriceTicketSellDateEnd(this.getTotalTicketOnDateMoney(convertToDateSql(reportItemMoney.getDateEnd())));
            } else {
                newStatistic.setType("");
            }
            if (!reportItemMoney.getCompareReportTimeSelection().equals("")){
                if (this.checkCompareReport(reportItemMoney)) {
                    newStatistic.setNumberTicketSellDateStartCompare(this.getTotalTicketOfMoney(convertToDateSql(reportItemMoney.getCompareReportDateStart()),convertToDateSql(reportItemMoney.getCompareReportDateEnd())));
                    newStatistic.setPriceTicketSellDateStartCompare(this.getTotalTicketOnDateMoney(convertToDateSql(reportItemMoney.getCompareReportDateStart())));
                    newStatistic.setPriceTicketSellDateEndCompare(this.getTotalTicketOnDateMoney(convertToDateSql(reportItemMoney.getCompareReportDateEnd())));
                } else {
                    newStatistic.setType("");
                }
            }
            newStatistic.setMessage("ok");
            System.out.println(newStatistic.toString());
        } catch (Exception e){
            newStatistic.setMessage("error");
            System.out.println(newStatistic.toString());
        }
        return newStatistic;
    }

    @Override
    public Statistic createFromReportItemBrand(Report reportItemBrand) {
        Statistic newStatistic = new Statistic();
        try{
            if (this.checkDateReport(reportItemBrand)) {
                newStatistic.setItem(reportItemBrand.getItemReport());
                newStatistic.setType(reportItemBrand.getTypeReport());
                newStatistic.setTimeSelectionCompare(reportItemBrand.getCompareReportTimeSelection());
                newStatistic.setNumberItem(this.getTotalOfBrand(convertToDateSql(reportItemBrand.getDateStart()),convertToDateSql(reportItemBrand.getDateEnd())));
                newStatistic.setDateStart(reportItemBrand.getDateStart());
                newStatistic.setDateEnd(reportItemBrand.getDateEnd());
                newStatistic.setCompareReportDateStart(reportItemBrand.getCompareReportDateStart());
                newStatistic.setCompareReportDateEnd(reportItemBrand.getCompareReportDateEnd());
                newStatistic.setNumberTicketSellDateStart(this.getNumberFlightOfBrand(convertToDateSql(reportItemBrand.getDateStart())));
                newStatistic.setNumberTicketSellDateEnd(this.getNumberFlightOfBrand(convertToDateSql(reportItemBrand.getDateEnd())));
                newStatistic.setPriceTicketSellDateStart(this.getTotalTicketOnDateBrand(convertToDateSql(reportItemBrand.getDateStart())));
                newStatistic.setPriceTicketSellDateEnd(this.getTotalTicketOnDateBrand(convertToDateSql(reportItemBrand.getDateEnd())));
            } else {
                newStatistic.setType("");
            }
            if (!reportItemBrand.getCompareReportTimeSelection().equals("")){
                if (this.checkCompareReport(reportItemBrand)) {
                    newStatistic.setNumberTicketSellDateStartCompare(this.getNumberFlightOfBrand(convertToDateSql(reportItemBrand.getCompareReportDateStart())));
                    newStatistic.setNumberTicketSellDateEndCompare(this.getNumberFlightOfBrand(convertToDateSql(reportItemBrand.getCompareReportDateEnd())));
                    newStatistic.setPriceTicketSellDateStartCompare(this.getTotalTicketOnDateBrand(convertToDateSql(reportItemBrand.getCompareReportDateStart())));
                    newStatistic.setPriceTicketSellDateEndCompare(this.getTotalTicketOnDateBrand(convertToDateSql(reportItemBrand.getCompareReportDateEnd())));
                } else {
                    newStatistic.setType("");
                }
            }
            newStatistic.setMessage("ok");
            System.out.println(newStatistic.toString());
        } catch (Exception e){
            newStatistic.setMessage("error");
            System.out.println(newStatistic.toString());
        }

        return newStatistic;
    }
}
