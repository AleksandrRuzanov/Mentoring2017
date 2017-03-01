package com.epam.mentoring.repository;

import com.epam.mentoring.models.Mentor;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends EmployeeRepository<Mentor> {

}