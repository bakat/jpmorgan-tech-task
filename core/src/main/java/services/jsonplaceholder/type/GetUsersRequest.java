package services.jsonplaceholder.type;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import services.handler.HttpRequestHandler;
import services.handler.HttpRequestType;
import services.handler.TestAutomationHttpResponse;
import services.jsonplaceholder.domain.UriParameter;
import services.jsonplaceholder.domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GetUsersRequest implements HttpRequestType {
    private String uri;
    private Object requestMessage;
    private HttpRequestHandler httpRequestHandler;
    private static final String usersUri = "users";

    @Value("${jsonplaceholder.host}")
    private String host;

    public GetUsersRequest(HttpRequestHandler httpRequestHandler) {
        this.httpRequestHandler = httpRequestHandler;
    }

    public void setHttpRequestUri(UriParameter httpRequestUri) {
        this.uri = String.format("%s/%s", host, usersUri);

        if(httpRequestUri != null){
            this.uri = String.format("%s/%s", this.uri, httpRequestUri.getFormattedParameter());
        }
    }

    public void setHttpRequestContent(Object requestMessage) {

    }

    public TestAutomationHttpResponse sendRequest() {
        return httpRequestHandler.performGetRequest(uri);
    }

    public Object parseResponse(TestAutomationHttpResponse response) throws IOException {
        Response rawResponse = response.getRawResponse();
        List<User> users = null;

        try {
            users = Arrays.asList(rawResponse.getBody().as(User[].class));
        } catch (Exception e){
            User singleUserResult = rawResponse.getBody().as(User.class);
            users = new ArrayList<User>();
            users.add(singleUserResult);
        }

        return users;
    }

    private String getUri(){
        if(this.uri == null){
            setHttpRequestUri(null);
        }
        return uri;
    }
}
