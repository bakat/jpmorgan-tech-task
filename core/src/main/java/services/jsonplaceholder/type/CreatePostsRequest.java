package services.jsonplaceholder.type;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import services.handler.HttpRequestHandler;
import services.handler.HttpRequestType;
import services.handler.TestAutomationHttpResponse;
import services.jsonplaceholder.domain.Post;
import services.jsonplaceholder.domain.UriParameter;

import java.io.IOException;

@Component
public class CreatePostsRequest implements HttpRequestType {
    private String uri;
    private Object requestMessage;
    private HttpRequestHandler httpRequestHandler;
    private static final String createPostUri = "posts";

    @Value("${jsonplaceholder.host}")
    private String host;

    public CreatePostsRequest(HttpRequestHandler httpRequestHandler) {
        this.httpRequestHandler = httpRequestHandler;
    }

    public void setHttpRequestUri(UriParameter httpRequestUri) {
        this.uri = String.format("%s/%s", host, createPostUri);
    }

    public void setHttpRequestContent(Object requestMessage) {
        this.requestMessage = requestMessage;
    }

    public TestAutomationHttpResponse sendRequest() {
        return httpRequestHandler.performPostRequest(getUri(), requestMessage);
    }

    public Post parseResponse(TestAutomationHttpResponse response) throws IOException {
        Response rawResponse = response.getRawResponse();
        Post newPost = rawResponse.getBody().as(Post.class);
        return newPost;
    }

    private String getUri(){
        if(this.uri == null){
            setHttpRequestUri(null);
        }
        return uri;
    }
}
