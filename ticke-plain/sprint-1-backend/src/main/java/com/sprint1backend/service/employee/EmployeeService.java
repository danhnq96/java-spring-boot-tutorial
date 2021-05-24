package com.sprint1backend.service.employee;

import com.sprint1backend.entity.AppAccount;
import com.sprint1backend.entity.AppRole;
import com.sprint1backend.entity.Employee;
import java.util.List;

/**
 * EmployeeService
 * <p>
 * Version
 * <p>
 * Date: 20-12-2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR                           DESCRIPTION
 * -----------------------------------------------------------------------------------
 * 20-12-2020           Mai_HTQ           CRUD / Validate / Account - Role / Search
 */
public interface EmployeeService {
    //-------------------- CRUD Employee ----------------------------
    List<Employee> findAllEmployee();

    Employee findEmployeeById(Long id);

    void saveEmployee(Employee employee);

    void editEmployee(Employee employee);

    void deleteEmployee(Long id);


    //------------------- Validate ton tai --------------------------
    List<Employee> findEmployeeByCode(String code);

    List<Employee> findEmployeeByEmail(String email);


    //------------------- Account - Role --------------------------
    void saveAccount(AppAccount appAccount);

    AppAccount findByNameAppAccount(String appAccount);

    AppRole findByNameAppRole(String appRole);

    AppRole findRole(Long id);


    //-------------------------- Search -------------------------------
    List<Employee> findEmployeeByFullNameContaining (String fullName);

    List<Employee> findEmployeeByEmailContaining (String email);

    List<Employee> findEmployeeByPhoneNumberContaining (String phoneNumber);
 }
