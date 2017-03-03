package com.epam.mentoring.service;

import com.epam.mentoring.controllers.RequestEmployee;
import com.epam.mentoring.models.Employee;
import com.epam.mentoring.repository.EmployeeRepository;
import com.epam.mentoring.service.exception.EmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aleksandr_Ruzanov on 28.02.2017.
 */

@Service
public abstract class EmployeeService<R extends EmployeeRepository, T extends Employee> {

    @Autowired
    private R repository;


    protected abstract T getInstanceEntity();

    public T create(T employee) throws EmployeeException {
        try {
            employee = (T) repository.save(employee);
        } catch (Exception ex) {
            throw new EmployeeException("Error creating the employee: " + ex.toString());
        }
        return employee;
    }

    public T create(String email, String name) throws EmployeeException {
        T employee;
        try {
            employee = getInstanceEntity();
            employee.setEmail(email);
            employee.setName(name);
            repository.save(employee);
        } catch (Exception ex) {
            throw new EmployeeException("Error creating the employee: " + ex.toString());
        }
        return employee;
    }

    public void delete(long id) throws EmployeeException {
        try {
            T employee = getInstanceEntity();
            employee.setId(id);
            repository.delete(employee);
        } catch (Exception ex) {
            throw new EmployeeException("Error deleting the employee: " + ex.toString());
        }
    }

    public T update(T employee) throws EmployeeException {
        try {
            repository.save(employee);
        } catch (Exception ex) {
            throw new EmployeeException("Error updating the employee: " + ex.toString());
        }
        return employee;
    }

    public T getEmployee(long id) throws EmployeeException {
        T employee;
        try {
            employee = (T) repository.findOne(id);
        } catch (Exception ex) {
            throw new EmployeeException("Error get the employee: " + ex.toString());
        }
        return employee;
    }

    public T create(RequestEmployee request) throws EmployeeException {
        T employee;
        employee = getInstanceEntity();
        employee.setEmail(request.getEmail());
        employee.setName(request.getName());
        employee = create(employee);
        return employee;
    }

    public T update(RequestEmployee request) throws EmployeeException {
        T employee;
        employee = getEmployee(request.getId());
        employee.setEmail(request.getEmail());
        employee.setName(request.getName());
        update(employee);
        return employee;
    }
}
