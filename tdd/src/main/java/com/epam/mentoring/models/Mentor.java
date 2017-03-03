package com.epam.mentoring.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr_Ruzanov on 28.02.2017.
 */

@Entity
@Table(name="mentors")
public class Mentor extends Employee{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mentor")
    private List<Mentee> mentees = new ArrayList<>();

    public Mentor() {
    }

    public Mentor(long id) {
        super(id);
    }

    public Mentor(String email, String name) {
        super(email, name);
    }


    public Mentor(long id, String email, String name) {
        super(id, email, name);
    }

    public List<Mentee> getMentees() {
        return mentees;
    }

    public void ListMentees(List<Mentee> mentees) {
        this.mentees = mentees;
    }

    public void addMentee(Mentee mentee){
        mentees.add(mentee);
    }

}
