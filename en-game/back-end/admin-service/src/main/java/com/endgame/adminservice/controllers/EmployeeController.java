package com.endgame.adminservice.controllers;

import com.endgame.adminservice.dto.employee.*;
import com.endgame.adminservice.sevices.EmployeeService;
import com.endgame.adminservice.sevices.MailService;
import com.endgame.adminservice.commons.FuncHelper;
import com.endgame.adminservice.commons.SendGridThread;
import com.endgame.adminservice.commons.SortData;
import com.endgame.adminservice.models.Employee;
import com.endgame.adminservice.models.ErrorAPI;
import com.endgame.adminservice.sevices.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/admin/employees")
//@CrossOrigin(origins = "http://localhost:4201", allowedHeaders = "*")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;
    @Autowired
    private AccountService accountService;
    FuncHelper funcHelper;

    //    API Get List Employees
    @GetMapping(value = "")
    public ResponseEntity<Page<ListEmployeeDTO>> getListEmployees(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                                  @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                                                  @RequestParam(name = "search", required = false, defaultValue = "") String search,
                                                                  @RequestParam(defaultValue = "id,asc") String[] sort) {
        Page<ListEmployeeDTO> lists = employeeService.getListEmployees(PageRequest.of(page, size, Sort.by(SortData.getOrderByParamSort(sort))), search);
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }

    //    API Get List To Export Excel
    @GetMapping(value = "/export")
    public ResponseEntity<List<EmployeeExportExcelDTO>> getListEmployeesExportToExcel(
            @RequestParam(name = "search", required = false, defaultValue = "") String search) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getListEmployeesExportToExcel(search).getContent());
    }

    //    API Get Employee By Id
    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findById(id));
    }

    //    API Edit Employee
    @PostMapping(value = "/{id}")
    public ResponseEntity<ErrorAPI> editEmployee(@Valid @RequestBody EmployeeDTO emp) {
        funcHelper = new FuncHelper();
        try {
            employeeService.saveEmployee(emp);
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorAPI("Success", emp.getAccount().getUsername(), 1));
        } catch (Exception err) {
            ErrorAPI errorAPI = funcHelper.getErrorSqlApi(err);
            return ResponseEntity.status(900).body(errorAPI);
        }
    }

    //    API Get Id By Username
    @GetMapping(value = "/profile")
    public ResponseEntity<Integer> getProfile(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getIdByUsername(authentication.getName()));
    }

    //    API Add List Employee
    @PostMapping(value = "/new-list")
    public ResponseEntity<List<ErrorAPI>> addListEmployee(@RequestBody @NotEmpty(message = "List Employee cannot be empty.") List<@Valid EmployeeDTO> emps) {
        if (emps.size() > 100) {
            List<ErrorAPI> errorAPIs = new ArrayList<>();
            errorAPIs.add(new ErrorAPI("Error Size", "Length of list can not larger 100", 1));
            return ResponseEntity.status(901).body(errorAPIs);
        }
        funcHelper = new FuncHelper();
        List<ErrorAPI> errorAPIs = new ArrayList<>();
        boolean check = true;
        for (int i = 0; i < emps.size(); i++) {
            EmployeeDTO employeeDTO = emps.get(i);
            employeeDTO = funcHelper.formatEmployee(employeeDTO, employeeService);
            String pass = funcHelper.getPassword();
            try {
                employeeService.addEmployee(employeeDTO, passwordEncoder.encode(pass));
                ErrorAPI errorAPI = new ErrorAPI();
                EmployeeFireBaseDTO employeeFireBaseDTO = new EmployeeFireBaseDTO();
                employeeFireBaseDTO.setId(employeeDTO.getAccount().getUsername());
                if (employeeDTO.getMidName().length() > 0) {
                    employeeFireBaseDTO.setName(employeeDTO.getLastName() + " " + employeeDTO.getMidName() + " " + employeeDTO.getFirstName());
                } else {
                    employeeFireBaseDTO.setName(employeeDTO.getLastName() + " " + employeeDTO.getFirstName());
                }
                employeeFireBaseDTO.setAvatar(employeeDTO.getImage());
                employeeFireBaseDTO.setStatus("offline");
                errorAPI.setEmployeeFireBaseDTO(employeeFireBaseDTO);
                errorAPI.setNo(0);
                errorAPIs.add(errorAPI);
//                send mail
//                add list
                SendGridThread t = new SendGridThread(String.valueOf(i), employeeDTO, pass, mailService);
                t.start();

            } catch (Exception err) {
                check = false;
                ErrorAPI errorAPI = funcHelper.getErrorSqlApi(err);
                errorAPI.setNo(i + 1);
                errorAPIs.add(errorAPI);
            }
        }
        if (!check) {
            return ResponseEntity.status(900).body(errorAPIs);
        }
        return ResponseEntity.status(HttpStatus.OK).body(errorAPIs);
    }

    //    API Add Employee
    @PostMapping(value = "/new")
    public ResponseEntity<ErrorAPI> addEmployee(@Valid @RequestBody EmployeeDTO emp) {
//        Auto Gen Username By Name Of Employee
        funcHelper = new FuncHelper();
        emp = funcHelper.formatEmployee(emp, employeeService);
        String pass = funcHelper.getPassword();
        try {
            employeeService.addEmployee(emp, passwordEncoder.encode(pass));
//            Mail mail = funcHelper.getMail(emp.getEmail(), "Your Account\nUsername: " + emp.getAccount().getUsername() + "\nPassword: " + pass, "End Game Project");
//            mailService.sendEmail(mail);


//            mailService.sendEmail("End Game Project", emp.getEmail(), "Username: " + emp.getAccount().getUsername() + "\nPassword New: " + pass);
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorAPI("Success", emp.getAccount().getUsername(), 1));
        } catch (Exception err) {
            ErrorAPI errorAPI = funcHelper.getErrorSqlApi(err);
            return ResponseEntity.status(900).body(errorAPI);
        }
    }

    //    API Register
    @GetMapping(value = "/register")
    public ResponseEntity<?> register() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    API Register
    @PostMapping(value = "/register")
    public ResponseEntity<RegisterEmployeeDTO> register(@Valid @RequestBody RegisterEmployeeDTO emp) {
        employeeService.addNewEmployee(emp);
        return ResponseEntity.status(HttpStatus.OK).body(emp);
    }

    //    API Add Employee
    @GetMapping(value = "/admin")
    public ResponseEntity<EmployeeFireBaseDTO> getInfoAdmin() {
        Employee employee = employeeService.getInfoAdmin();
        EmployeeFireBaseDTO employeeFireBaseDTO = new EmployeeFireBaseDTO();
        employeeFireBaseDTO.setStatus("online");
        employeeFireBaseDTO.setId("admin");
        employeeFireBaseDTO.setAvatar(employee.getImage());
        if (employee.getMidName().length() > 0) {
            employeeFireBaseDTO.setName(employee.getLastName() + " " + employee.getMidName() + " " + employee.getFirstName());
        } else {
            employeeFireBaseDTO.setName(employee.getLastName() + " " + employee.getFirstName());
        }
        return ResponseEntity.status(200).body(employeeFireBaseDTO);
    }

}
