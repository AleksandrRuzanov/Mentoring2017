package com.epam.mentoring.controllers;

import com.epam.mentoring.models.Mentee;
import com.epam.mentoring.service.MenteeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/mentee")
public class MenteeController extends EmployeeController<MenteeService, Mentee>{

    //http://localhost:8080/mentee/create?email=333&name=444

}
