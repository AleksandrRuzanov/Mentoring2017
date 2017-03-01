package com.epam.mentoring.service;

import com.epam.mentoring.models.Mentee;
import com.epam.mentoring.repository.MenteeRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Aleksandr_Ruzanov on 28.02.2017.
 */

@Service
public class MenteeService extends EmployeeService<MenteeRepository, Mentee> {

    @Override
    protected Mentee getInstanceEntity() {
        return new Mentee();
    }
}
