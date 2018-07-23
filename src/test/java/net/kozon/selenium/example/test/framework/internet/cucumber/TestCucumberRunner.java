package net.kozon.selenium.example.test.framework.internet.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/net/kozon/selenium/example/test/framework/internet/cucumber/features/", glue = {"net.kozon.selenium.example.test.framework.internet.cucumber.StepDefinition"})
public class TestCucumberRunner {
}
