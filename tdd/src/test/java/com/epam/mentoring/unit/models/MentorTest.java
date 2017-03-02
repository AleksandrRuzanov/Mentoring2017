package com.epam.mentoring.unit.models;

/**
 * Created by Aleksandr_Ruzanov on 02.03.2017.
 */

import com.epam.mentoring.models.Mentor;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;

@RunWith(JUnit4.class)
public class MentorTest {
    private static Validator validator;
    private static ValidatorFactory factory;

    @Before
    public void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterClass
    public static void close() {
        factory.close();
    }

    @Test
    public void testMentorSuccess() {
        Mentor mentor = new Mentor("222", "222");
        Set<ConstraintViolation<Mentor>> violations = validator.validate(mentor);
        if (violations.size() > 0)
            violations.forEach(x -> System.out.println("Error:" + x.getMessage()));
        assertTrue(violations.isEmpty());
    }

}
