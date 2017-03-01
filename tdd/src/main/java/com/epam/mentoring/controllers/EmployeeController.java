package com.epam.mentoring.controllers;

import com.epam.mentoring.models.Employee;
import com.epam.mentoring.service.EmployeeService;
import com.epam.mentoring.service.exception.EmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public abstract class EmployeeController<T extends EmployeeService> {

    @Autowired
    protected T employeeService;

    @RequestMapping("/create")
    @ResponseBody
    public String create(String email, String name) {
        Employee employee = null;
        try {
            employee = employeeService.create(email, name);
        } catch (EmployeeException ex) {
            return "Error create the employee: " + ex.toString();
        }
        return "Employee succesfully created! (id = " + employee.getId() + ")";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            employeeService.delete(id);
        } catch (EmployeeException ex) {
            return "Error deleting the employee: " + ex.toString();
        }
        return "employee succesfully deleted!";
    }


    @RequestMapping("/update")
    @ResponseBody
    public String update(long id, String email, String name) {
        try {
            employeeService.update(id, email, name);
        } catch (EmployeeException ex) {
            return "Error updating the employee: " + ex.toString();
        }
        return "employee succesfully updated!";
    }

    @RequestMapping("/show")
    @ResponseBody
    public String show(long id) {
        Employee employee;
        try {
            employee = employeeService.getEmployee(id);
        } catch (EmployeeException ex) {
            return "Error updating the employee: " + ex.toString();
        }
        return employee.toString();
    }

}
