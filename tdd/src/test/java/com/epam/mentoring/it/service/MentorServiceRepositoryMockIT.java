package com.epam.mentoring.it.service;

import com.epam.mentoring.Application;
import com.epam.mentoring.models.Mentee;
import com.epam.mentoring.models.Mentor;
import com.epam.mentoring.repository.MenteeRepository;
import com.epam.mentoring.repository.MentorRepository;
import com.epam.mentoring.service.MenteeService;
import com.epam.mentoring.service.MentorService;
import com.epam.mentoring.service.exception.EmployeeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by Aleksandr_Ruzanov on 02.03.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class MentorServiceRepositoryMockIT {

    @Autowired
    private MentorService mentorService;
    @Autowired
    private MenteeService menteeService;

    @MockBean
    private MentorRepository mentorRepository;
    @MockBean
    private MenteeRepository menteeRepository;

    @Test
    public void testGetEmail() throws EmployeeException {
        given(mentorRepository.findOne(1L)).willReturn(new Mentor(1, "111", "222"));
        assertThat(mentorService.getEmployee(1).getEmail()).isEqualTo("111");
    }

    @Test
    public void testCountMentee() throws EmployeeException {
        Mentor mentor = new Mentor(2, "111", "222");

        given(menteeRepository.findOne(2L)).willReturn(new Mentee(2, "111", "222", mentor));
        given(menteeRepository.findOne(3L)).willReturn(new Mentee(3, "111", "222", mentor));

        mentor.addMentee(menteeService.getEmployee(2));
        mentor.addMentee(menteeService.getEmployee(3));

        given(mentorRepository.findOne(1L)).willReturn(mentor);

        assertThat(mentorService.getEmployee(1).getMentees().size()).isEqualTo(2);
    }

}
