package com.sprint1backend.service.employee;

import com.sprint1backend.entity.AppAccount;
import com.sprint1backend.entity.AppRole;
import com.sprint1backend.entity.Employee;
import com.sprint1backend.repository.AppAccountRepository;
import com.sprint1backend.repository.AppRoleRepository;
import com.sprint1backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * EmployeeServiceImpl
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
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AppAccountRepository appAccountRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    // -------------------------------- CRUD Employee --------------------------------------
    @Override
    public List<Employee> findAllEmployee() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public void editEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        this.employeeRepository.deleteById(id);
    }


    // ------------------------------- Validate ton tai -------------------------------------
    @Override
    public List<Employee> findEmployeeByCode(String code) {
        return this.employeeRepository.findEmployeeByEmployeeCode(code);
    }

    @Override
    public List<Employee> findEmployeeByEmail(String email) {
        return this.employeeRepository.findEmployeeByEmail(email);
    }

    // ------------------------------- Account - Role --------------------------------------------
    @Override
    public void saveAccount(AppAccount appAccount) {
        this.appAccountRepository.save(appAccount);
    }

    @Override
    public AppAccount findByNameAppAccount(String appAccount) {
        return appAccountRepository.findByUsername(appAccount);
    }

    @Override
    public AppRole findByNameAppRole(String appRole) {
        return appRoleRepository.findByName(appRole);
    }


    @Override
    public AppRole findRole(Long id) {
        AppRole appRole = new AppRole();
        AppAccount appAccount = this.appAccountRepository.findById(id).orElse(null);
        if (appAccount != null) {
            List<AppRole> appRoleList = this.appRoleRepository.findAll();
            for (AppRole appRoleExist : appRoleList) {
                if (appAccount.getAppRole().getId().equals(appRoleExist.getId())) {
                    appRole = appRoleExist;
                    break;
                }
            }
        }
        return appRole;
    }

    // ----------------------------- Search ---------------------------------
    @Override
    public List<Employee> findEmployeeByFullNameContaining(String fullName) {
        return this.employeeRepository.findEmployeeByFullNameContaining(fullName);
    }

    @Override
    public List<Employee> findEmployeeByEmailContaining(String email) {
        return this.employeeRepository.findEmployeeByEmailContaining(email);
    }

    @Override
    public List<Employee> findEmployeeByPhoneNumberContaining(String phoneNumber) {
        return this.employeeRepository.findEmployeeByPhoneNumberContaining(phoneNumber);
    }
}
