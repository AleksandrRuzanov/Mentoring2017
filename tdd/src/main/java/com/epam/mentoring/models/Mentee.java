package com.epam.mentoring.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Aleksandr_Ruzanov on 28.02.2017.
 */

@Entity
@Table(name = "mentees")
public class Mentee extends Employee {

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    public Mentee() {
    }

    public Mentee(String email, String name) {
        super(email, name);
    }

    public Mentee(long id) {
        super(id);
    }

    public Mentee(long id, String email, String name, Mentor mentor) {
        super(id, email, name);
        this.mentor = mentor;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    @Override
    public String toString() {
        return "Mentee{" +
                "mentorId=" + mentor.getId() +
                "} " + super.toString();
    }
}
