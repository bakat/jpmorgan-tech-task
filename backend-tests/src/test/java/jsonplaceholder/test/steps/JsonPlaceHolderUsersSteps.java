package jsonplaceholder.test.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.Assert;
import services.handler.TestAutomationServiceMessage;
import services.jsonplaceholder.domain.UriParameter;
import services.jsonplaceholder.domain.User;
import services.jsonplaceholder.testExecution.JsonPlaceHolderServiceRequest;
import services.jsonplaceholder.type.GetUsersRequest;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
public class JsonPlaceHolderUsersSteps {
    @Autowired
    private GetUsersRequest getUsersRequest;
    @Autowired
    private JsonPlaceHolderServiceRequest jsonPlaceHolderServiceRequest;

    @Given("I want to get a list of all users")
    public void i_want_to_get_a_list_of_all_users() {
        jsonPlaceHolderServiceRequest.setMessage(new TestAutomationServiceMessage(
                null, getUsersRequest, null));
    }

    @Given("I want to filter the users by the id number {int}")
    public void i_want_to_filter_the_users_by_the_id_number(Integer idNumber) {
        jsonPlaceHolderServiceRequest.setMessage(new TestAutomationServiceMessage(
                null, getUsersRequest, new UriParameter(String.valueOf(idNumber))));
    }

    @Then("The response message should contain the same data returned from the list of Users")
    public void the_response_message_should_contain_the_same_data_returned_from_the_list_of_Users() throws IOException {
        List<User> firstUserReuest = (List<User>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();
        User filteredUser = firstUserReuest.iterator().next();

        jsonPlaceHolderServiceRequest.setMessage(new TestAutomationServiceMessage(
                null, getUsersRequest, null));
        jsonPlaceHolderServiceRequest.sendMessage();
        Assert.assertTrue(jsonPlaceHolderServiceRequest.getService().getRawResponse().getStatusCode() == 200);

        List<User> users = (List<User>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        Boolean foundUser = false;
        for (User user : users) {
            if(user.getId() == filteredUser.getId()){
                foundUser = true;
                Assert.assertEquals(user.getName(), filteredUser.getName());
                Assert.assertEquals(user.getUsername(), filteredUser.getUsername());
                Assert.assertEquals(user.getEmail(), filteredUser.getEmail());
                Assert.assertEquals(user.getAddress().getStreet(), filteredUser.getAddress().getStreet());
                Assert.assertEquals(user.getAddress().getSuite(), filteredUser.getAddress().getSuite());
                Assert.assertEquals(user.getAddress().getCity(), filteredUser.getAddress().getCity());
                Assert.assertEquals(user.getAddress().getZipcode(), filteredUser.getAddress().getZipcode());
                Assert.assertEquals(user.getAddress().getGeo().getLat(), filteredUser.getAddress().getGeo().getLat());
                Assert.assertEquals(user.getAddress().getGeo().getLng(), filteredUser.getAddress().getGeo().getLng());
                Assert.assertEquals(user.getPhone(), filteredUser.getPhone());
                Assert.assertEquals(user.getWebsite(), filteredUser.getWebsite());
            }
        }
        Assert.assertTrue(foundUser);
    }

    @Then("The response message should contain one result")
    public void the_response_message_should_contain_one_result() throws IOException{
        List<User> users = (List<User>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        Assert.assertTrue(users.size() == 1);
    }

    @Then("The response message should contain {int} users")
    public void the_response_message_should_contain_users(Integer numberOfUsers) throws IOException{
        List<User> users = (List<User>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        Assert.assertTrue(users.size() == numberOfUsers);
    }

    @Then("All users should contain data populated")
    public void all_users_should_contain_data_populated() throws IOException{
        List<User> users = (List<User>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        for (User user : users) {
            Assert.assertNotNull(user.getId());
            Assert.assertNotNull(user.getName());
            Assert.assertNotNull(user.getUsername());
            Assert.assertNotNull(user.getEmail());
            Assert.assertNotNull(user.getAddress().getStreet());
            Assert.assertNotNull(user.getAddress().getSuite());
            Assert.assertNotNull(user.getAddress().getCity());
            Assert.assertNotNull(user.getAddress().getZipcode());
            Assert.assertNotNull(user.getAddress().getGeo().getLat());
            Assert.assertNotNull(user.getAddress().getGeo().getLng());
            Assert.assertNotNull(user.getPhone());
            Assert.assertNotNull(user.getWebsite());
            Assert.assertNotNull(user.getCompany().getName());
            Assert.assertNotNull(user.getCompany().getCatchPhrase());
            Assert.assertNotNull(user.getCompany().getBs());
        }
    }
}
