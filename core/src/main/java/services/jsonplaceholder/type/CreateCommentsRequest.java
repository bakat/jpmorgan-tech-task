package services.jsonplaceholder.type;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import services.handler.HttpRequestHandler;
import services.handler.HttpRequestType;
import services.handler.TestAutomationHttpResponse;
import services.jsonplaceholder.domain.Comment;
import services.jsonplaceholder.domain.UriParameter;

import java.io.IOException;

@Component
public class CreateCommentsRequest implements HttpRequestType {
    private String uri;
    private Object requestMessage;
    private HttpRequestHandler httpRequestHandler;
    private static final String createCommentsUri = "comments";

    @Value("${jsonplaceholder.host}")
    private String host;

    public CreateCommentsRequest(HttpRequestHandler httpRequestHandler) {
        this.httpRequestHandler = httpRequestHandler;
    }

    public void setHttpRequestUri(UriParameter httpRequestUri) {
        this.uri = String.format("%s/%s", host, createCommentsUri);
    }

    public void setHttpRequestContent(Object requestMessage) {
        this.requestMessage = requestMessage;
    }

    public TestAutomationHttpResponse sendRequest() {
        return httpRequestHandler.performPostRequest(getUri(), requestMessage);
    }

    public Object parseResponse(TestAutomationHttpResponse response) throws IOException {
        Response rawResponse = response.getRawResponse();
        Comment newComment = rawResponse.getBody().as(Comment.class);
        return newComment;
    }

    private String getUri(){
        if(this.uri == null){
            setHttpRequestUri(null);
        }
        return uri;
    }
}
