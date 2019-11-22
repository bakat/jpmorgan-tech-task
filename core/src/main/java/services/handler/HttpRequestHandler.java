package services.handler;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.*;

@Component
public class HttpRequestHandler {

    private Logger log = LoggerFactory.getLogger(HttpRequestHandler.class);

    public HttpRequestHandler() {
    }

    public TestAutomationHttpResponse performGetRequest(String uri){
        TestAutomationHttpResponse testAutomationHttpResponse = new TestAutomationHttpResponse();

        logRequestInfos("GET", uri);
        Response response = given().contentType(ContentType.JSON).get(uri);
        testAutomationHttpResponse.setRawResponse(response);
        testAutomationHttpResponse.setStatusCode(response.getStatusCode());
        logResponseInfos(response);

        return testAutomationHttpResponse;
    }

    public TestAutomationHttpResponse performPostRequest(String uri, Object message){
        TestAutomationHttpResponse testAutomationHttpResponse = new TestAutomationHttpResponse();

        logRequestInfos("POST", uri);
        Response response = given().contentType(ContentType.JSON).body(message).when().post(uri);
        testAutomationHttpResponse.setRawResponse(response);
        testAutomationHttpResponse.setStatusCode(response.getStatusCode());
        logResponseInfos(response);

        return testAutomationHttpResponse;
    }

    private void logRequestInfos(String requestType, String uri) {
        log.info(String.format("Performing a %s request to: ", requestType));
        log.info(uri);
    }

    private void logResponseInfos(Response response){
        log.info("Service response:");
        response.getBody().prettyPrint();
    }
}
