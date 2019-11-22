package jsonplaceholder.test.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.Assert;
import services.jsonplaceholder.testExecution.JsonPlaceHolderServiceRequest;

@RunWith(SpringRunner.class)
public class JsonPlaceHolderCommonSteps {
    @Autowired
    private JsonPlaceHolderServiceRequest jsonPlaceHolderServiceRequest;

    @When("I submit my request")
    public void i_submit_my_request() {
        jsonPlaceHolderServiceRequest.sendMessage();
    }

    @Then("The response should return with success")
    public void the_response_should_return_with_success() {
        Integer statusCode = jsonPlaceHolderServiceRequest.getService().getRawResponse().getStatusCode();
        Assert.assertTrue(statusCode == 200 || statusCode == 201);
    }

    @Then("The response message should return an error")
    public void the_response_message_should_return_an_error() {
        Assert.assertTrue(jsonPlaceHolderServiceRequest.getService().getRawResponse().getStatusCode() == 404);
    }
}
