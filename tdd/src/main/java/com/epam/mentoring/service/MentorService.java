package com.epam.mentoring.service;

import com.epam.mentoring.models.Mentee;
import com.epam.mentoring.models.Mentor;
import com.epam.mentoring.repository.MentorRepository;
import com.epam.mentoring.service.exception.EmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aleksandr_Ruzanov on 28.02.2017.
 */
@Service
public class MentorService extends EmployeeService<MentorRepository, Mentor> {

    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private MenteeService menteeService;

    @Override
    protected Mentor getInstanceEntity() {
        return new Mentor();
    }

    public Mentor addMentee(long mentorId, long menteeId) throws EmployeeException {
        Mentor mentor;
        Mentee mentee;
        mentor = mentorRepository.findOne(mentorId);
        mentee = menteeService.getEmployee(menteeId);
        mentor.addMentee(mentee);
        mentee.setMentor(mentor);
        mentorRepository.save(mentor);
        menteeService.create(mentee);
        return mentor;
    }

}
