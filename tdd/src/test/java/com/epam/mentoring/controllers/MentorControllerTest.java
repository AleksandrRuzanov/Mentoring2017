package com.epam.mentoring.controllers;

import com.epam.mentoring.models.Mentee;
import com.epam.mentoring.models.Mentor;
import com.epam.mentoring.service.MentorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Aleksandr_Ruzanov on 01.03.2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MentorController.class)
public class MentorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MentorService mentorService;

    @Test
    public void testShow() throws Exception {
        Mentor mentor = new Mentor(2, "111", "222");
        mentor.addMentee(new Mentee(3, "111", "222", mentor));
        mentor.addMentee(new Mentee(4, "111", "222", mentor));
        given(this.mentorService.getEmployee(2)).willReturn(mentor);
        this.mvc.perform(get("/mentor/show?id={id}", 2).accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()).andExpect(content().string("Mentor{mentees=[Mentee{mentorId=2} Employee{id=3, email='111', name='222'}, Mentee{mentorId=2} Employee{id=4, email='111', name='222'}]} Employee{id=2, email='111', name='222'}"));
    }
}
