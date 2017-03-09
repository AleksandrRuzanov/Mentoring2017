package com.epam.mentoring.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Aleksandr_Ruzanov on 06.03.2017.
 */

@RunWith(Cucumber.class)
@CucumberOptions(format = "pretty", features = "src/test/resources/cucumber")
public class CucumberTestRunner {
}
