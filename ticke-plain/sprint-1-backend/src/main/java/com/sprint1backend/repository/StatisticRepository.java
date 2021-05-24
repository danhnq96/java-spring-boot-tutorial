package com.sprint1backend.repository;

import com.sprint1backend.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
//    Employee's method
    @Query(nativeQuery = true, value = "select max(id) as number_employee\n" +
            "from employee;")
    public long getNumberEmployee();

    @Query(nativeQuery = true, value = "select t.price_arrival+t.price_departure as price_ticket_ondate\n" +
            "from ticket t\n" +
            "inner join flight_information f on t.id = f.id \n" +
            "where arrival_date like ?;")
    public long getPriceTicketOnDateEmployee(java.sql.Date date);

    @Query(nativeQuery = true, value = "select count(t.id) as number_ticket_sold\n" +
            "from ticket t\n" +
            "inner join flight_information f on t.id = f.id \n" +
            "where arrival_date like ?;")
    public long getNumberTicketSoldOnDateEmployee(java.sql.Date date);

//    Money's method
    @Query(nativeQuery = true, value = "select sum(price_arrival)+sum(price_departure) as total\n" +
            "from  ticket\n" +
            "where status_payment_id between 2 and 3\n" +
            "group by status_payment_id;")
    public long getTotalMoney();

    @Query(nativeQuery = true, value = "select count(t.id) as numberTicket\n" +
            "from  ticket t\n" +
            "inner join flight_information f on t.id = f.id \n" +
            "where arrival_date = ? or arrival_date= ?;")
    public long getTotalTicket(java.sql.Date dateStart, java.sql.Date dateEnd);

    @Query(nativeQuery = true, value = "select sum(t.price_arrival)+sum(t.price_departure) as numberOnDate\n" +
            "from  ticket t\n" +
            "inner join flight_information f on t.id = f.id \n" +
            "where arrival_date = ?;")
    public long getTotalMoneyOnDate(java.sql.Date date);

//    Brand's method
    @Query(nativeQuery = true, value = "select count(id)\n" +
            "from flight_information\n" +
            "where arrival_date = ? or arrival_date = ? ;")
    public long getTotalFlight(java.sql.Date dateStart, java.sql.Date dateEnd);

    @Query(nativeQuery = true, value = "select count(arrival_date)\n" +
            "from flight_information\n" +
            "where arrival_date = ?;")
    public long getNumberFlightOnDate(java.sql.Date date);

    @Query(nativeQuery = true, value = "select sum(price)\n" +
            "from flight_information\n" +
            "where arrival_date = ?;")
    public long getTotalPriceFlightOnDate(java.sql.Date date);
}
