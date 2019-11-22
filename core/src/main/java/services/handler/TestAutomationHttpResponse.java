package services.handler;

import io.restassured.response.Response;

public class TestAutomationHttpResponse {
    private Response rawResponse;
    private Integer statusCode;

    public Response getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(Response rawResponse) {
        this.rawResponse = rawResponse;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
