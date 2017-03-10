package com.epam.mentoring.cucumber;

import com.epam.mentoring.HttpHelper;
import com.epam.mentoring.TestApplicationConfig;
import com.epam.mentoring.person.Person;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Aleksandr_Ruzanov on 06.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
public class PersonSteps {

    private static final String URL_REQUEST = "http://localhost:8080/person/";
    private Person person;
    private Person personRequest;

    @Given("^some persons$")
    public void givenPerson(List<Person> persons) {
        this.person = persons.get(0);
    }

    @When("^create person")
    public void createPerson() {
        personRequest = HttpHelper.sendRequest(URL_REQUEST + "create", RequestMethod.POST, "id=" + this.person.getId() + "&email=" + this.person.getEmail() + "&name=" + this.person.getName());
    }

    @When("^get person (\\d+)")
    public void getPerson(long id) {
        createPerson();
        personRequest = HttpHelper.sendRequest(URL_REQUEST + "get/" + id, RequestMethod.GET, null);
    }

    @When("^update person email '(.+)' name '(.+)'")
    public void updatePerson(String email, String name) {
        createPerson();
        personRequest = HttpHelper.sendRequest(URL_REQUEST + "update", RequestMethod.PUT, "id=" + this.person.getId() + "&email=" + email + "&name=" + name);
        person = HttpHelper.sendRequest(URL_REQUEST + "get/" + this.person.getId(), RequestMethod.GET, null);
    }

    @When("^delete person (\\d+)")
    public void deletePerson(long id) {
        createPerson();
        HttpHelper.sendRequest(URL_REQUEST + "delete/" + id, RequestMethod.DELETE, null);
        personRequest = HttpHelper.sendRequest(URL_REQUEST + "get/" + id, RequestMethod.GET, null);
    }

    @Then("^result should be equals Given and When")
    public void thenScenario() {
        Assert.assertEquals(person, personRequest);
    }

    @Then("^delete result should be person is null")
    public void thenDeleteScenario() {
        Assert.assertNull(personRequest);
    }

}