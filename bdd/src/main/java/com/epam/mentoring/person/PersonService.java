package com.epam.mentoring.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aleksandr_Ruzanov on 28.02.2017.
 */

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Person create(Person person) throws PersonException {
        try {
            person = repository.save(person);
        } catch (Exception ex) {
            throw new PersonException("Error creating the com.epam.mentoring.person: " + ex.toString());
        }
        return person;
    }

    public void delete(long id) throws PersonException {
        try {
            Person person = new Person();
            person.setId(id);
            repository.delete(person);
        } catch (Exception ex) {
            throw new PersonException("Error deleting the employee: " + ex.toString());
        }
    }

    public Person update(Person person) throws PersonException {
        try {
            repository.save(person);
        } catch (Exception ex) {
            throw new PersonException("Error updating the com.epam.mentoring.person: " + ex.toString());
        }
        return person;
    }

    public Person getPerson(long id) throws PersonException {
        Person person;
        try {
            person = repository.findOne(id);
        } catch (Exception ex) {
            throw new PersonException("Error get the com.epam.mentoring.person: " + ex.toString());
        }
        return person;
    }
}
