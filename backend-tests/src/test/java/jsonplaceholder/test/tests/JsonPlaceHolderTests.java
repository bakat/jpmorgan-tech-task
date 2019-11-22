package jsonplaceholder.test.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(plugin = {"html:target/test-report"},
        features = {
                "src/test/resources/features/JsonPlaceHolderPostsScenarios.feature",
                "src/test/resources/features/JsonPlaceHolderCommentsScenarios.feature",
                "src/test/resources/features/JsonPlaceHolderUsersScenarios.feature"},
        glue = {"jsonplaceholder.test.steps"})
public class JsonPlaceHolderTests extends AbstractTestNGCucumberTests {
}
