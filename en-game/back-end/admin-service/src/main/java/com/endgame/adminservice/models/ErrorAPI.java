package com.endgame.adminservice.models;

import com.endgame.adminservice.dto.employee.EmployeeFireBaseDTO;

public class ErrorAPI {
    private String name;
    private String content;
    private int no;
    private EmployeeFireBaseDTO employeeFireBaseDTO;



    public ErrorAPI(String name, String content, int no) {
        this.name = name;
        this.content = content;
        this.no = no;
    }

    public ErrorAPI() {
        this.name = "Success";
        this.content = "Ok";
        this.no = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public EmployeeFireBaseDTO getEmployeeFireBaseDTO() {
        return employeeFireBaseDTO;
    }

    public void setEmployeeFireBaseDTO(EmployeeFireBaseDTO employeeFireBaseDTO) {
        this.employeeFireBaseDTO = employeeFireBaseDTO;
    }
}
