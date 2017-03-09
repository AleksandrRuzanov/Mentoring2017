package com.epam.mentoring.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {

    @Autowired
    protected PersonService personService;

    @RequestMapping(value = "/create1", method = RequestMethod.POST)
    public ResponseEntity<Person> create(Person request) {
        Person employee;
        try {
            employee = (Person) personService.create(request);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete1/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Person> delete(long id) {
        try {
            Person employee = personService.getPerson(id);
            if (employee != null && employee.getId() > 0) {
                personService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (PersonException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/update1", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Person> update(Person request) {
        Person person;
        try {
            person = personService.update(request);
        } catch (PersonException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @RequestMapping(value = "/show1/{id}", method = RequestMethod.GET)
    @ResponseBody()
    public ResponseEntity<Person> show(@PathVariable("id") long id) {
        Person person;
        try {
            person = personService.getPerson(id);
            if (person != null && person.getId() > 0)
                return new ResponseEntity<>(person, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (PersonException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
