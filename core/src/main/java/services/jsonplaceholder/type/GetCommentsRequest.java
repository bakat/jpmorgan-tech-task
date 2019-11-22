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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GetCommentsRequest implements HttpRequestType {
    private String uri;
    private Object requestMessage;
    private HttpRequestHandler httpRequestHandler;
    private static final String commentsUri = "comments";

    @Value("${jsonplaceholder.host}")
    private String host;

    public GetCommentsRequest(HttpRequestHandler httpRequestHandler) {
        this.httpRequestHandler = httpRequestHandler;
    }

    public void setHttpRequestUri(UriParameter httpRequestUri) {
        this.uri = "";
        String filter = "";

        if(httpRequestUri != null){
            filter = "?" + httpRequestUri.getFormattedParameter();
        }

        this.uri = String.format("%s/%s%s", host, commentsUri, filter);
    }

    public void setHttpRequestContent(Object requestMessage) {

    }

    public TestAutomationHttpResponse sendRequest() {
        return httpRequestHandler.performGetRequest(getUri());
    }

    public List<Comment> parseResponse(TestAutomationHttpResponse response) throws IOException {
        Response rawResponse = response.getRawResponse();
        List<Comment> comments = null;

        try {
            comments = Arrays.asList(rawResponse.getBody().as(Comment[].class));
        } catch (Exception e){
            Comment singleCommentResult  = rawResponse.getBody().as(Comment.class);
            comments = new ArrayList<Comment>();
            comments.add(singleCommentResult);
        }

        return comments;
    }

    private String getUri(){
        if(this.uri == null){
            setHttpRequestUri(null);
        }
        return uri;
    }
}
