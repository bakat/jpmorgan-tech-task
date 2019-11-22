package services.jsonplaceholder;

import services.handler.HttpRequestType;
import services.handler.TestAutomationHttpResponse;
import services.handler.TestAutomationService;
import services.handler.TestAutomationServiceMessage;

import java.io.IOException;

public class JsonPlaceHolderService implements TestAutomationService {
    private HttpRequestType httpRequestType;
    private TestAutomationHttpResponse response;

    public Object getParsedResponse() throws IOException {
        if(response.getRawResponse() != null){
            return httpRequestType.parseResponse(response);
        }
        return null;
    }

    public TestAutomationHttpResponse getRawResponse() {
        return response;
    }

    public void sendRequest() {
        response = httpRequestType.sendRequest();
    }

    public void setRequestMessage(TestAutomationServiceMessage message) {
        httpRequestType = message.getHttpRequestType();
        httpRequestType.setHttpRequestUri(message.getUriParameter());
        httpRequestType.setHttpRequestContent(message.getContent());
    }

    public HttpRequestType getHttpRequestType() {
        return httpRequestType;
    }
}
