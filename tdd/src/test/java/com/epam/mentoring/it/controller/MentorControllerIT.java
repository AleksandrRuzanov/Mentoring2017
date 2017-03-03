package com.epam.mentoring.it.controller;

import com.epam.mentoring.Application;
import com.epam.mentoring.controllers.MentorController;
import com.epam.mentoring.controllers.RequestEmployee;
import com.epam.mentoring.models.Mentee;
import com.epam.mentoring.models.Mentor;
import com.epam.mentoring.service.MentorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Aleksandr_Ruzanov on 01.03.2017.
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application_it.properties")
@ContextConfiguration(classes = {Application.class})
@WebMvcTest(MentorController.class)
public class MentorControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private MentorService mentorService;
    @Autowired
    private MentorController mentorController;

    private MediaType contentType = new MediaType(MediaType.MULTIPART_FORM_DATA.getType(),
            MediaType.MULTIPART_FORM_DATA.getSubtype());

    @Test
    public void testCreate() throws Exception {
        String postBody = mapper.writeValueAsString(new RequestEmployee(1,"4444", "111"));
        this.mvc.perform(post("/mentee/create/?&email=1111&name=00000000").contentType(contentType).content(postBody)).andExpect(status().isCreated());
    }

    @Test
    public void testShow() throws Exception {
        Mentor mentor = new Mentor(2, "111", "222");
        mentor.addMentee(new Mentee(3, "111", "222", mentor));
        mentor.addMentee(new Mentee(4, "111", "222", mentor));
        mentorService.create(mentor);
        given(this.mentorService.getEmployee(2)).willReturn(mentor);
        this.mvc.perform(get("/mentor/show/{id}", 2))
                .andExpect(status().isOk());
    }
}
