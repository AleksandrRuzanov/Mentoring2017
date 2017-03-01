package com.epam.mentoring.repository;


import com.epam.mentoring.models.Mentee;
import org.springframework.stereotype.Repository;

@Repository
public interface MenteeRepository extends EmployeeRepository<Mentee> {

}