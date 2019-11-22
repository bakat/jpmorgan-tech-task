package jsonplaceholder.test.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import services.jsonplaceholder.testExecution.JsonPlaceHolderServiceRequest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.Assert;
import services.handler.TestAutomationServiceMessage;
import services.jsonplaceholder.domain.Comment;
import services.jsonplaceholder.domain.UriParameter;
import services.jsonplaceholder.type.CreateCommentsRequest;
import services.jsonplaceholder.type.GetCommentsRequest;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
public class JsonPlaceHolderCommentsSteps {
    @Autowired
    private GetCommentsRequest getCommentsRequest;
    @Autowired
    private CreateCommentsRequest createCommentsRequest;
    @Autowired
    private JsonPlaceHolderServiceRequest jsonPlaceHolderServiceRequest;

    private Comment comment;

    @Given("I want to get a list of all comments posted")
    public void i_want_to_get_a_list_of_all_comments_posted() {
        jsonPlaceHolderServiceRequest.setMessage(new TestAutomationServiceMessage(
                null, getCommentsRequest, null));
    }

    @Given("I want to comment on a post with the id {int}")
    public void i_want_to_comment_on_a_post_with_the_id(Integer postId) {
        comment = new Comment();
        comment.setPostId(postId);
    }

    @Given("the comments name is {string}")
    public void the_comments_name_is(String name) {
        comment.setName(name);
    }

    @Given("the comments email is {string}")
    public void the_comments_email_is(String email) {
        comment.setEmail(email);
    }

    @Given("the comments content is {string}")
    public void the_comments_content_is(String content) {
        comment.setBody(content);
    }

    @When("I request the creation of my comment")
    public void i_request_the_creation_of_my_comment() {
        jsonPlaceHolderServiceRequest.setMessage(new TestAutomationServiceMessage(
                comment, createCommentsRequest, null));
        jsonPlaceHolderServiceRequest.sendMessage();
    }

    @Given("I want to filter the comments by the post id number {int}")
    public void i_want_to_filter_the_comments_by_the_post_id_number(Integer idNumber) {
        jsonPlaceHolderServiceRequest.setMessage(new TestAutomationServiceMessage(
                null, getCommentsRequest, new UriParameter("postId", String.valueOf(idNumber))));
    }

    @Then("The response message should contain five results")
    public void the_response_message_should_contain_five_results() throws IOException{
        List<Comment> comments = (List<Comment>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        Assert.assertTrue(comments.size() == 5);
    }

    @Then("The response message should contain an id greater than five hundred")
    public void the_response_message_should_contain_an_id_greater_than_five_hundred() throws IOException{
        Comment comment = (Comment) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        Assert.assertTrue(comment.getId() > 500);
    }

    @Then("The response message should contain the comment data")
    public void the_response_message_should_contain_the_comment_data() throws IOException{
        Comment response = (Comment) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        Assert.assertEquals(response.getPostId(), comment.getPostId());
        Assert.assertEquals(response.getName(), comment.getName());
        Assert.assertEquals(response.getEmail(), comment.getEmail());
        Assert.assertEquals(response.getBody(), comment.getBody());
    }

    @Then("The response message should contain the same data returned from the list of Comments")
    public void the_response_message_should_contain_the_same_data_returned_from_the_list_of_Comments() throws IOException {
        List<Comment> filteredComments = (List<Comment>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        jsonPlaceHolderServiceRequest.setMessage(new TestAutomationServiceMessage(
                null, getCommentsRequest, null));
        jsonPlaceHolderServiceRequest.sendMessage();
        Assert.assertTrue(jsonPlaceHolderServiceRequest.getService().getRawResponse().getStatusCode() == 200);

        List<Comment> listOfComments = (List<Comment>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        for (Comment comment : filteredComments) {
            Comment commentFromList = getCommentsFromList(comment.getId(), listOfComments);
            Assert.assertNotNull(commentFromList);

            Assert.assertTrue(commentFromList.getPostId() == comment.getPostId());
            Assert.assertEquals(commentFromList.getName(), comment.getName());
            Assert.assertEquals(commentFromList.getEmail(), comment.getEmail());
            Assert.assertEquals(commentFromList.getBody(), comment.getBody());
        }
    }

    private Comment getCommentsFromList(int id, List<Comment> listOfComments) {
        for (Comment commentFromList : listOfComments) {
            if(commentFromList.getId() == id){
                return commentFromList;
            }
        }
        return null;
    }

    @Then("The response message should contain {int} comments")
    public void the_response_message_should_contain_comments(Integer numberOfComments) throws IOException{
        List<Comment> comments = (List<Comment>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        Assert.assertTrue(comments.size() == numberOfComments);
    }

    @Then("All comments should contain data populated")
    public void all_comments_should_contain_data_populated() throws IOException{
        List<Comment> comments = (List<Comment>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        for (Comment comment : comments) {
            Assert.assertNotNull(comment.getPostId());
            Assert.assertNotNull(comment.getId());
            Assert.assertNotNull(comment.getName());
            Assert.assertNotNull(comment.getEmail());
            Assert.assertNotNull(comment.getBody());
        }
    }

    @Then("The response message should not contain any results")
    public void the_response_message_should_not_contain_any_results() throws IOException{
        List<Comment> comments = (List<Comment>) jsonPlaceHolderServiceRequest.getService().getParsedResponse();

        Assert.assertTrue(comments.size() == 0);
    }

}
