package com.endgame.adminservice.commons;

import com.endgame.adminservice.models.ErrorAPI;
import com.endgame.adminservice.sevices.EmployeeService;
import com.endgame.adminservice.dto.employee.AccountDTO;
import com.endgame.adminservice.dto.employee.EmployeeDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FuncHelper {

    public EmployeeDTO formatEmployee(EmployeeDTO emp, EmployeeService employeeService) {
        String username = FormatString.getUsernameByName(emp.getFirstName(), emp.getMidName(), emp.getLastName());
        String tempUsername = employeeService.findUsername(username);
        AccountDTO account = new AccountDTO();
        emp.setAccount(account);
        emp.getAccount().setUsername(FormatString.getUsernameNumberAfterCheckExist(username, tempUsername));
        return emp;
    }

    public ErrorAPI getErrorSqlApi(Exception err) {
        if(err.getCause().getCause() !=null) {
            String message = err.getCause().getCause().getMessage();
            ErrorAPI errorAPI = new ErrorAPI();
            if (message.contains("UNIQUE KEY constraint")) {
                System.out.println(message);
                String val = message.substring(message.indexOf("The duplicate key value is ("), message.lastIndexOf(")."));
                val = val.substring(val.indexOf("(") + 1);
                errorAPI.setName("Duplicate!");
                errorAPI.setContent("\"" + val + "\" already exist!");
            } else {
                errorAPI.setName("Error SQL");
                errorAPI.setContent(message);
            }
            return errorAPI;
        }
        else {
            return null;
        }


    }

    public String convertDateToString(String pattern, Date date){
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public String getPassword() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
