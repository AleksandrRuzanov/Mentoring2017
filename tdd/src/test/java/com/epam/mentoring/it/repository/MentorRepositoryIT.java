package com.epam.mentoring.it.repository;

import com.epam.mentoring.Application;
import com.epam.mentoring.models.Mentor;
import com.epam.mentoring.repository.MentorRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Aleksandr_Ruzanov on 01.03.2017.
 */

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application_it.properties")
@ContextConfiguration(classes = {Application.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:data.xml")
public class MentorRepositoryIT {

    @Autowired
    MentorRepository mentorRepository;

    @Test
    public void testGetAll() {
        List<Mentor> all = (List<Mentor>) mentorRepository.findAll();
        assertThat(all, is(notNullValue()));
        assertThat(all.size(), is(2));
    }

}
