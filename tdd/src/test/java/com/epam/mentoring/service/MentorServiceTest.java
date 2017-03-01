package com.epam.mentoring.service;

import com.epam.mentoring.AbstractTest;
import com.epam.mentoring.models.Mentee;
import com.epam.mentoring.models.Mentor;
import com.epam.mentoring.service.MentorService;
import com.epam.mentoring.service.exception.EmployeeException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by Aleksandr_Ruzanov on 01.03.2017.
 */

public class MentorServiceTest extends AbstractTest {

    @MockBean
    private MentorService mentorService;

    @Before
    public void setup() {
    }

    @Test
    public void testGetEmail() throws EmployeeException {
        given(this.mentorService.getEmployee(1)).willReturn(new Mentor("111", "222"));
        assertThat(this.mentorService.getEmployee(1).getEmail()).isEqualTo("111");
    }

    @Test
    public void testCountMentee() throws EmployeeException {
        Mentor mentor = new Mentor(2, "111", "222");
        mentor.addMentee(new Mentee(3, "111", "222", mentor));
        mentor.addMentee(new Mentee(4, "111", "222", mentor));
        given(this.mentorService.getEmployee(2)).willReturn(mentor);
        assertThat(this.mentorService.getEmployee(2).getMentees().size()).isEqualTo(2);
    }

}
