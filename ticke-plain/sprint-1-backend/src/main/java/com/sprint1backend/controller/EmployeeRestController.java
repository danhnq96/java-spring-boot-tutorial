package com.sprint1backend.controller;


import com.sprint1backend.entity.AppAccount;
import com.sprint1backend.entity.AppRole;
import com.sprint1backend.entity.Employee;
import com.sprint1backend.model.EmployeeDTO;
import com.sprint1backend.service.employee.EmployeeService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * EmployeeRestController
 * <p>
 * Version
 * <p>
 * Date: 18-12-2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * --------------------------------------------------
 * 18-12-2020           Mai_HTQ           CRUD
 */

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    /**
     * Get List Employee
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getListEmployee() {
        List<Employee> listEmployee = this.employeeService.findAllEmployee();
        return new ResponseEntity<>(listEmployee, HttpStatus.OK);
    }

    /**
     * Get List Role
     *
     * @return
     */
    @GetMapping("/find-role/{id}")
    public ResponseEntity<AppRole> getRole(@PathVariable Long id) {
        AppRole appRole = this.employeeService.findRole(id);
        return new ResponseEntity<>(appRole, HttpStatus.OK);
    }

    /**
     * Find Employee By ID
     *
     * @param id
     * @return
     */
    @GetMapping("/findEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = this.employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * Delete Employee By ID
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Long id) {
        Employee employee = this.employeeService.findEmployeeById(id);
        employee.getAppAccount().setAppRole(null);
        this.employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create Employee
     *
     * @param EmployeeDTO
     * @return 
     */
    @PostMapping("/createNew")
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO EmployeeDTO) {

        AppAccount appAccount = new AppAccount();
        appAccount.setUsername(EmployeeDTO.getEmail());
        appAccount.setPassword(bcryptEncoder.encode(EmployeeDTO.getPassword()));
        AppRole role = this.employeeService.findByNameAppRole(EmployeeDTO.getRole());
        appAccount.setAppRole(role);
        this.employeeService.saveAccount(appAccount);

        Employee employee = new Employee();
        employee.setEmployeeCode(EmployeeDTO.getEmployeeCode());
        employee.setFullName(EmployeeDTO.getFullName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(EmployeeDTO.getBirthday(), formatter);
        employee.setBirthday(localDate);
        if (EmployeeDTO.getGender()) {
            employee.setGender(true);
        } else {
            employee.setGender(false);
        }
        employee.setEmail(EmployeeDTO.getEmail());
        employee.setPhoneNumber(EmployeeDTO.getPhoneNumber());

        AppAccount appAccount1 = employeeService.findByNameAppAccount(EmployeeDTO.getEmail());
        employee.setAppAccount(appAccount1);
        String randomCode = RandomString.make(64);
        appAccount.setVerificationCode(randomCode);
        appAccount.setEnabled(true);
        employeeService.saveAccount(appAccount);

        this.employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Edit Employee
     *
     * @param 
     * @param id
     * @return
     */
    @PutMapping("/editEmployee/{id}")
    public ResponseEntity<Void> editEmployee(@RequestBody EmployeeDTO EmployeeDTO, @PathVariable long id) {
        Employee employee1 = employeeService.findEmployeeById(id);
        if (employee1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            AppAccount appAccount = employeeService.findByNameAppAccount(employee1.getAppAccount().getUsername());
            AppRole role = employeeService.findByNameAppRole(EmployeeDTO.getRole());
            appAccount.setAppRole(role);
            this.employeeService.saveAccount(appAccount);

            employee1.setEmployeeCode(EmployeeDTO.getEmployeeCode());
            employee1.setFullName(EmployeeDTO.getFullName());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse(EmployeeDTO.getBirthday(), formatter);
            employee1.setBirthday(localDate);
            if (!EmployeeDTO.getGender()) {
                employee1.setGender(false);
            } else {
                employee1.setGender(true);
            }
            employee1.setEmail(EmployeeDTO.getEmail());
            employee1.setPhoneNumber(EmployeeDTO.getPhoneNumber());
            employeeService.editEmployee(employee1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    // -------------------------------- Validate ton tai ----------------------------------
    /**
     * Find Employee By employeeCode
     *
     * @param employeeCode
     * @return
     */
    @GetMapping("/findEmployeeByCode")
    public ResponseEntity<List<Employee>> getEmployeeByCode(@RequestParam String employeeCode) {
        List<Employee> employeeList = this.employeeService.findEmployeeByCode(employeeCode);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    /**
     * Find Employee By email
     *
     * @param email
     * @return
     */
    @GetMapping("/findEmployeeByEmail")
    public ResponseEntity<List<Employee>> getEmployeeByEmail(@RequestParam String email) {
        List<Employee> employeeList = this.employeeService.findEmployeeByEmail(email);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    //------------------------------ Search Employee ----------------------------------
    /**
     * Search Employee by full name
     *
     * @param fullName
     * @return
     */
    @GetMapping("/searchFullName/{fullName}")
    public ResponseEntity<List<Employee>> searchFullNameEmployee(@PathVariable String fullName) {
        List<Employee> listEmployee = this.employeeService.findEmployeeByFullNameContaining(fullName);
        return new ResponseEntity<>(listEmployee, HttpStatus.OK);
    }

    /**
     * Search Employee by phone number
     *
     * @param phoneNumber
     * @return
     */
    @GetMapping("/searchPhoneNumber/{phoneNumber}")
    public ResponseEntity<List<Employee>> searchPhoneNumberEmployee(@PathVariable String phoneNumber) {
        List<Employee> listEmployee = this.employeeService.findEmployeeByPhoneNumberContaining(phoneNumber);
        return new ResponseEntity<>(listEmployee, HttpStatus.OK);
    }

    /**
     * Search Employee by email
     *
     * @param email
     * @return
     */
    @GetMapping("/searchEmail/{email}")
    public ResponseEntity<List<Employee>> searchEmailEmployee(@PathVariable String email) {
        List<Employee> listEmployee = this.employeeService.findEmployeeByEmailContaining(email);
        return new ResponseEntity<>(listEmployee, HttpStatus.OK);
    }
}
