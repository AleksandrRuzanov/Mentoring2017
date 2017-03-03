package com.epam.mentoring.controllers;

import com.epam.mentoring.models.Employee;
import com.epam.mentoring.service.EmployeeService;
import com.epam.mentoring.service.exception.EmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public abstract class EmployeeController<S extends EmployeeService, E extends Employee> {

    @Autowired
    protected S employeeService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<E> create(RequestEmployee request) {
        E employee;
        try {
            employee = (E) employeeService.create(request);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);// employeeService.create(employee);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<E> delete(long id) {
        try {
            Employee employee = employeeService.getEmployee(id);
            if (employee != null && employee.getId() > 0) {
                employeeService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (EmployeeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<E> update(RequestEmployee request) {
        E employee;
        try {
            employee = (E) employeeService.update(request);
        } catch (EmployeeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    @ResponseBody()
    public ResponseEntity<E> show(@PathVariable("id") long id) {
        E employee;
        try {
            employee = (E) employeeService.getEmployee(id);
            if (employee != null && employee.getId() > 0)
                return new ResponseEntity<>(employee, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (EmployeeException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
