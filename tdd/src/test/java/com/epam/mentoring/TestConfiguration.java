package com.epam.mentoring;

import com.epam.mentoring.controllers.MenteeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Aleksandr_Ruzanov on 01.03.2017.
 */


public class TestConfiguration {

    public MenteeController menteeController() {
        return new MenteeController();
    }
}
