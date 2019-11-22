package jsonplaceholder.test.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import services.jsonplaceholder.testExecution.JsonPlaceHolderServiceRequest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.Assert;
import services.JsonPlaceHolderTestAutomation;
import services.handler.TestAutomationServiceMessage;
import services.jsonplaceholder.configuration.JsonPlaceHolderConfiguration;
import services.jsonplaceholder.domain.Post;
import services.jsonplaceholder.domain.UriParameter;
import services.jsonplaceholder.type.*;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        JsonPlaceHolderTestAutomation.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(classes = JsonPlaceHolderConfiguration.class)
public class JsonPlaceHolderPostsSteps {
    @Autowired
    private GetPostsRequest getPostsRequest;
    @Autowired
    private CreatePostsRequest createPostsRequest;
    @Autowired
    private JsonPlaceHolderServiceRequest jsonPlaceHolderServiceRequest;

    private Post post;

    @Given("I want to get a list of all posts created")
    public void i_want_to_get_a_list_of_all_posts_created() {
        jsonPlaceHolderServiceRequest.setMessage(new TestAutomationServiceMessage(
                null, getPostsRequest, null));
    }

    @Then("The response message should contain {int} posts")
    public void the_response_message_should_contain_posts(Integer numberOfPosts) throws IOException {
        List<Post> posts = (List<Post>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        Assert.assertTrue(posts.size() == numberOfPosts);
    }

    @Then("All posts should contain data populated")
    public void all_posts_should_contain_data_populated() throws IOException{
        List<Post> posts = (List<Post>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        for (Post post : posts) {
            Assert.assertNotNull(post.getUserId());
            Assert.assertNotNull(post.getId());
            Assert.assertNotNull(post.getTitle());
            Assert.assertNotNull(post.getBody());
        }
    }

    @Given("I want to create a new post with the title {string}")
    public void i_want_to_create_a_new_post_with_the_title(String title) {
        post = new Post();
        post.setTitle(title);
    }

    @Given("the posts content is {string}")
    public void the_posts_content_is(String content) {
        post.setBody(content);
    }

    @Given("the posts userId is {int}")
    public void the_posts_userId_is(Integer userId) {
        post.setUserId(userId);
    }

    @When("I request the post creation")
    public void i_request_the_post_creation() {
        jsonPlaceHolderServiceRequest.setMessage(new TestAutomationServiceMessage(
                post, createPostsRequest, null));
        jsonPlaceHolderServiceRequest.sendMessage();
    }

    @Then("The response message should contain an id greater than a hundred")
    public void the_response_message_should_contain_an_id_greater_than_a_hundred() throws IOException{
        Post response = (Post) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        Assert.assertTrue(response.getId() > 100);
    }

    @Then("The response message should contain the data I sent in")
    public void the_response_message_should_contain_the_data_I_sent_in() throws IOException{
        Post response = (Post) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        Assert.assertEquals(response.getTitle(), post.getTitle());
        Assert.assertEquals(response.getBody(), post.getBody());
        Assert.assertEquals(response.getUserId(), post.getUserId());
    }

    @Given("I want to filter the posts by the post id number {int}")
    public void i_want_to_filter_the_posts_by_the_post_id_number(Integer idNumber) {
        UriParameter urlParameter = new UriParameter(String.valueOf(idNumber));
        jsonPlaceHolderServiceRequest.setMessage(new TestAutomationServiceMessage(
                null, getPostsRequest, urlParameter));
    }

    @Then("The response message should contain {int} results")
    public void the_response_message_should_contain_results(Integer numberOfResults) throws IOException{
        List<Post> posts = (List<Post>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        Assert.assertTrue(posts.size() == numberOfResults);
    }

    @Then("The response message should contain the same data returned from the list of Posts")
    public void the_response_message_should_contain_the_same_data_returned_from_the_list_of_Posts() throws IOException{
        List<Post> firstPostRequest = (List<Post>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();
        Post detailedPost = firstPostRequest.iterator().next();

        jsonPlaceHolderServiceRequest.setMessage(new TestAutomationServiceMessage(
                null, getPostsRequest, null));
        jsonPlaceHolderServiceRequest.sendMessage();
        Assert.assertTrue(jsonPlaceHolderServiceRequest.getService().getRawResponse().getStatusCode() == 200);

        List<Post> listOfPosts = (List<Post>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        Boolean foundPost = false;
        for (Post post : listOfPosts) {
            if(post.getId() == detailedPost.getId()){
                foundPost = true;
                Assert.assertTrue(post.getUserId() == detailedPost.getUserId());
                Assert.assertEquals(post.getTitle(), detailedPost.getTitle());
                Assert.assertEquals(post.getBody(), detailedPost.getBody());
            }
        }
        Assert.assertTrue(foundPost);
    }
}
