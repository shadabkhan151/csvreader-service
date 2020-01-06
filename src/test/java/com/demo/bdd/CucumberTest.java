package com.demo.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

/**
 * To run cucumber test.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources"}, plugin={"pretty", "html:target/cucumber-reports"}, snippets = SnippetType.CAMELCASE)
public class CucumberTest{
}