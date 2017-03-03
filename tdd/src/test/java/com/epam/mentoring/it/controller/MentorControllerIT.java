package com.epam.mentoring.it.controller;

import com.epam.mentoring.Application;
import com.epam.mentoring.controllers.MentorController;
import com.epam.mentoring.controllers.RequestEmployee;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    @Test
    public void testCreate() throws Exception {
        Mentor mentor = new Mentor("111", "222");
        when(this.mentorService.create(any(RequestEmployee.class))).thenReturn(mentor);
        String postBody = mapper.writeValueAsString(new RequestEmployee(1, "111", "222"));
        this.mvc.perform(post("/mentor/create").contentType(contentType).content(postBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("222")))
                .andExpect(jsonPath("$.email", is("111")));
    }

    @Test
    public void testShow() throws Exception {
        Mentor mentor = new Mentor(2, "111", "222");
        mentorService.create(mentor);
        when(this.mentorService.getEmployee(2)).thenReturn(mentor);
        this.mvc.perform(get("/mentor/show/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("222")));
    }
}
