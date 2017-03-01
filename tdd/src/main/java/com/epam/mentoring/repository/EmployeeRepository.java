package com.epam.mentoring.repository;

import com.epam.mentoring.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Aleksandr_Ruzanov on 28.02.2017.
 */

@Repository
@Transactional
public interface EmployeeRepository<T extends Employee> extends CrudRepository<T, Long> {
}
