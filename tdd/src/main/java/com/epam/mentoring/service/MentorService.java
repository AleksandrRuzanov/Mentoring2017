package com.epam.mentoring.service;

import com.epam.mentoring.models.Mentee;
import com.epam.mentoring.models.Mentor;
import com.epam.mentoring.repository.MentorRepository;
import com.epam.mentoring.service.exception.EmployeeException;
import org.springframework.stereotype.Service;

/**
 * Created by Aleksandr_Ruzanov on 28.02.2017.
 */
@Service
public class MentorService extends EmployeeService<MentorRepository, Mentor> {

    @Override
    protected Mentor getInstanceEntity() {
        return new Mentor();
    }

    public Mentor addMentee(long mentorId, long menteeId) throws EmployeeException {
        Mentor mentor;
        Mentee mentee;
        mentor = mentorDao.findOne(mentorId);
        mentee = menteeDao.findOne(menteeId);
        mentor.addMentee(mentee);
        mentee.setMentor(mentor);
        mentorDao.save(mentor);
        menteeDao.save(mentee);
        return mentor;
    }

}
