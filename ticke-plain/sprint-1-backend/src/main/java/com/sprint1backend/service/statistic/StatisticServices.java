package com.sprint1backend.service.statistic;


import com.sprint1backend.entity.Report;
import com.sprint1backend.entity.Statistic;

public interface StatisticServices {
    long getNumberEmployee();
    long getTotalMoney();
    long getTotalTicketOfMoney(java.sql.Date dateStart, java.sql.Date dateEnd);
    long getTotalOfBrand(java.sql.Date dateStart, java.sql.Date dateEnd);
//    void createNewStatistic(Report report);
//    void deleteStatistic(Long id);
    long getPriceTicketOnDateEmployee(java.sql.Date date);
    long getTotalTicketOnDateMoney(java.sql.Date date);
    long getTotalTicketOnDateBrand(java.sql.Date date);
    long getNumberFlightOfBrand(java.sql.Date date);
    long getNumberTicketSoldOnDateEmployee(java.sql.Date date);
    Statistic createFromReportItemEmployee(Report reportItemEmployee);
    Statistic createFromReportItemMoney(Report reportItemMoney);
    Statistic createFromReportItemBrand(Report reportItemBrand);
}
