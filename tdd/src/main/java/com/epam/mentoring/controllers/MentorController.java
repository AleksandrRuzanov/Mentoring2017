package com.epam.mentoring.controllers;

import com.epam.mentoring.models.Mentor;
import com.epam.mentoring.service.MentorService;
import com.epam.mentoring.service.exception.EmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/mentor")
public class MentorController extends EmployeeController<MentorService, Mentor> {

    //http://localhost:8080/mentee/create?email=333&name=444
    //http://localhost:8080/mentor/addmentee?mentorId=1&menteeId=1
    @RequestMapping(value = "{id}/addmentee", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Mentor> addMentee(long id, RequestEmployee mentee) {
        Mentor mentor;
        try {
            mentor = employeeService.addMentee(id, mentee.getId());
        } catch (EmployeeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mentor, HttpStatus.OK);
    }
}
