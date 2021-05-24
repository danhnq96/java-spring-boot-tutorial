package com.endgame.adminservice.sevices;

import com.endgame.adminservice.dto.employee.EmployeeDTO;
import com.endgame.adminservice.dto.employee.EmployeeExportExcelDTO;
import com.endgame.adminservice.dto.employee.ListEmployeeDTO;
import com.endgame.adminservice.dto.employee.RegisterEmployeeDTO;
import com.endgame.adminservice.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    void addNewEmployee(RegisterEmployeeDTO emp);
    Page<ListEmployeeDTO> getListEmployees(Pageable pageable, String search);
    Page<EmployeeExportExcelDTO> getListEmployeesExportToExcel(String search);
    EmployeeDTO findById(int id);
    String findUsername(String username);
    void saveEmployee(EmployeeDTO employeeDTO);
    void addEmployee(EmployeeDTO employeeDTO, String pass);
    Integer getIdByUsername(String username);
    String findEmailByUsername(String username);
    Employee getInfoAdmin();
}
