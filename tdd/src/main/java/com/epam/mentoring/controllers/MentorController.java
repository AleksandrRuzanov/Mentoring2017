package com.epam.mentoring.controllers;

import com.epam.mentoring.service.MentorService;
import com.epam.mentoring.service.exception.EmployeeException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/mentor")
public class MentorController extends EmployeeController<MentorService> {

    //http://localhost:8080/mentee/create?email=333&name=444

    //http://localhost:8080/mentor/addmentee?mentorId=1&menteeId=1
    @RequestMapping("/addmentee")
    @ResponseBody
    public String addMentee(long mentorId, long menteeId) {
        try {
            employeeService.addMentee(mentorId, menteeId);
        } catch (EmployeeException ex) {
            return "Error add the mentee: " + ex.toString();
        }
        return "Able to link Mentee to Mentor created!";
    }
}
