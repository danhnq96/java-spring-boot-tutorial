package com.endgame.adminservice.repositories;

import com.endgame.adminservice.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT emp FROM Employee emp " +
            "WHERE emp.firstName LIKE %:search% " +
            "OR emp.account.username LIKE %:search% " +
            "OR emp.midName LIKE %:search% " +
            "OR emp.lastName LIKE %:search% " +
            "OR emp.address LIKE %:search% " +
            "OR emp.phone LIKE %:search% " +
            "OR emp.birthday LIKE %:search%"
    )
    Page<Employee> getListEmployees(String search, Pageable pageable);

    Employee findById(int id);

    //    Employee findByEmail(String email);
//    Employee findByPhone(String phone);
//    Employee findByIdCard(String idCard);
    @Query("SELECT emp FROM Employee emp " +
            "WHERE emp.account.username LIKE :username% " +
            "ORDER BY emp.account.username desc"
    )
    List<Employee> findUsername(@Param("username") String username);

    @Query("SELECT emp FROM Employee emp " +
            "WHERE emp.account.username = :username"
    )
    Employee findEmployeeByUsername(@Param("username") String username);
}
