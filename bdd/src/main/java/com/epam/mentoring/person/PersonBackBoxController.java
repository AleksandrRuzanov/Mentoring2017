package com.epam.mentoring.person;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/person")
public class PersonBackBoxController {

    private Map<Long, Person> personMap = new HashMap<>();

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Person> create(Person request) {
        personMap.put(request.getId(), request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Person> delete(@PathVariable("id") long id) {
        if (personMap.get(id) != null && personMap.get(id).getId() == id) {
            personMap.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Person> update(Person request) {
        if (personMap.get(request.getId()) != null && personMap.get(request.getId()).getId() == request.getId()) {
            personMap.put(request.getId(), request);
            return new ResponseEntity<>(request, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody()
    public ResponseEntity<Person> show(@PathVariable("id") long id) {
        if (personMap.get(id) != null && personMap.get(id).getId() == id) {
            return new ResponseEntity<>(personMap.get(id), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
