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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GetPostsRequest implements HttpRequestType {
    private String uri;
    private Object requestMessage;
    private HttpRequestHandler httpRequestHandler;
    private static final String createPostUri = "posts";

    @Value("${jsonplaceholder.host}")
    private String host;

    public GetPostsRequest(HttpRequestHandler httpRequestHandler) {
        this.httpRequestHandler = httpRequestHandler;
    }

    public void setHttpRequestUri(UriParameter httpRequestUri) {
        this.uri = String.format("%s/%s", host, createPostUri);

        if(httpRequestUri != null){
            this.uri = String.format("%s/%s", this.uri, httpRequestUri.getFormattedParameter());
        }
    }

    public void setHttpRequestContent(Object requestMessage) {
        this.requestMessage = requestMessage;
    }

    public TestAutomationHttpResponse sendRequest() {
        return httpRequestHandler.performGetRequest(getUri());
    }

    public List<Post> parseResponse(TestAutomationHttpResponse response) throws IOException {
        Response rawResponse = response.getRawResponse();
        List<Post> posts = null;

        try {
            posts = Arrays.asList(rawResponse.getBody().as(Post[].class));
        } catch (Exception e){
            Post singlePostResult = rawResponse.getBody().as(Post.class);
            posts = new ArrayList<Post>();
            posts.add(singlePostResult);
        }

        return posts;
    }

    private String getUri(){
        if(this.uri == null){
            setHttpRequestUri(null);
        }
        return uri;
    }
}
