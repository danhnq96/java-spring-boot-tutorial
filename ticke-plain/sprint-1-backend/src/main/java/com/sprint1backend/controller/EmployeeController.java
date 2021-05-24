package com.sprint1backend.controller;

import com.sprint1backend.entity.AppAccount;
import com.sprint1backend.entity.Employee;
import com.sprint1backend.repository.AppAccountRepository;
import com.sprint1backend.repository.EmployeeRepository;
import com.sprint1backend.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin()
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    AppAccountRepository appAccountRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value = "/findEmployee/{employeeId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getEmployeeById(@PathVariable("employeeId") Long id) {
        AppAccount account = appAccountRepository.findById(id).orElse(null);
        Employee employee = employeeRepository.findByAppAccount(account);
        return ResponseEntity.ok(employee);
    }

}
