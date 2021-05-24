package com.sprint1backend.repository;

import com.sprint1backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.sprint1backend.entity.AppAccount;
import com.sprint1backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Create by: Mai_HTQ _ Find function
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findEmployeeByFullNameContaining (String fullName);

    List<Employee> findEmployeeByEmailContaining (String email);

    List<Employee> findEmployeeByPhoneNumberContaining (String phoneNumber);


    // ----------------------- Validate ton tai ---------------------------
    List<Employee> findEmployeeByEmployeeCode (String employeeCode);

    List<Employee> findEmployeeByEmail (String email);

    Optional<Employee> findByChangePassToken(String changePassToken);
    Optional<Employee> findByEmail(String email);

    Employee findByAppAccount(AppAccount account);
}




