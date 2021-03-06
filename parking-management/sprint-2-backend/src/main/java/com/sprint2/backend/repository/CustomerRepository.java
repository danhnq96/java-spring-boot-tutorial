package com.sprint2.backend.repository;

import com.sprint2.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * nguyen quoc khanh
     * @param id
     * @return
     */
    @Query(value = "select * from customer where app_account_id = ?",nativeQuery = true)
    Customer findCustomerByAppAccountId(Long id);

    /**
     * Nguyen Quang Danh
     *
     * @return Thống kê số lượng khách hàng
     */
    @Query(nativeQuery = true, value = "select count(*) as total_customer from project2_parking_management.customer")
    Long getTotalCustomer();

    /**
     * @param fromDay
     * @param toDay
     * @return Thống kê số lượng khách hàng đăng ký trong khoảng thời gian
     */
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object" +
            "('date_register', project2_parking_management.statistics_total_customer.date_register,\n" +
            "    'total_customer', project2_parking_management.statistics_total_customer.total_customer))\n" +
            "from project2_parking_management.statistics_total_customer\n" +
            "where project2_parking_management.statistics_total_customer.date_register\n" +
            "between (?1)\n" +
            "and (?2)")
    Object getToTalCustomerRegisterPeriod(LocalDate fromDay, LocalDate toDay);

    // ----------------------- Vinh Begin -------------------------
    Customer findByAppAccountId(Long id);
    // ----------------------- Vinh End -------------------------

    /**
     * Lanh
     * @param fullName
     * @return Customer
     */
    Customer findByFullName(String fullName);

    Boolean existsByEmail(String email);

    Customer findByEmail(String email);
}
