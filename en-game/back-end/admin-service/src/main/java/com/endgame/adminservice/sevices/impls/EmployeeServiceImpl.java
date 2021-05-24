package com.endgame.adminservice.sevices.impls;

import com.endgame.adminservice.commons.FormatString;
import com.endgame.adminservice.dto.employee.EmployeeDTO;
import com.endgame.adminservice.dto.employee.EmployeeExportExcelDTO;
import com.endgame.adminservice.dto.employee.ListEmployeeDTO;
import com.endgame.adminservice.dto.employee.RegisterEmployeeDTO;
import com.endgame.adminservice.models.Account;
import com.endgame.adminservice.models.Employee;
import com.endgame.adminservice.sevices.EmployeeService;
import com.endgame.adminservice.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public void addNewEmployee(RegisterEmployeeDTO emp) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        Employee employee = modelMapper.map(emp, Employee.class);
        employeeRepository.save(employee);
    }

    @Override
    public Page<ListEmployeeDTO> getListEmployees(Pageable pageable, String search) {
        ModelMapper modelMapper = new ModelMapper();
        Page<Employee> listEmployees = employeeRepository.getListEmployees(search, pageable);
        return listEmployees.map(employee -> modelMapper.map(employee, ListEmployeeDTO.class));
    }

    @Override
    public Page<EmployeeExportExcelDTO> getListEmployeesExportToExcel(String search) {
        ModelMapper modelMapper = new ModelMapper();
        Page<Employee> listEmployees = employeeRepository.getListEmployees(search, PageRequest.of(0,Integer.MAX_VALUE));
        return listEmployees.map(employee -> modelMapper.map(employee, EmployeeExportExcelDTO.class));
    }

    @Override
    public EmployeeDTO findById(int id) {
        ModelMapper modelMapper = new ModelMapper();
        Employee employee = employeeRepository.findById(id);
        return modelMapper.map(employee, EmployeeDTO.class);
    }


//    @Override
//    public Employee findByEmail(String email) {
//        return employeeRepository.findByEmail(email);
//    }
//
//    @Override
//    public Employee findByPhone(String phone) {
//        return employeeRepository.findByPhone(phone);
//    }
//
//    @Override
//    public Employee findByIdCard(String idCard) {
//        return employeeRepository.findByIdCard(idCard);
//    }

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        Employee emp = employeeRepository.findById(employeeDTO.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(employeeDTO, emp);
        employeeRepository.save(emp);
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO, String pass) {
        Employee emp = new Employee();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(employeeDTO, emp);
        Account account = new Account();
        account.setActive(true);
        account.setPassword(pass);
        account.setUsername(employeeDTO.getAccount().getUsername());
        account.setEmployee(emp);
        emp.setActive(true);
        emp.setAccount(account);
        emp.setFirstName(FormatString.formatName(emp.getFirstName()));
        emp.setMidName(FormatString.formatName(emp.getMidName()));
        emp.setLastName(FormatString.formatName(emp.getLastName()));
        employeeRepository.save(emp);
    }

    @Override
    public Integer getIdByUsername(String username) {
        return employeeRepository.findEmployeeByUsername(username).getId();
    }

    @Override
    public String findEmailByUsername(String username) {
        return employeeRepository.findEmployeeByUsername(username).getEmail();
    }

    @Override
    public Employee getInfoAdmin() {
        return employeeRepository.findEmployeeByUsername("admin");
    }

    @Override
    public String findUsername(String username) {
        List<Employee> employees = new ArrayList<>(employeeRepository.findUsername(username));
        String usernameTemp = "";
        if (employees.size() > 0) {
            usernameTemp = employees.get(0).getAccount().getUsername();
        }
        return usernameTemp;
    }


}
