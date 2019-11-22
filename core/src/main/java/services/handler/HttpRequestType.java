package services.handler;

import services.jsonplaceholder.domain.UriParameter;

import java.io.IOException;

public interface HttpRequestType {
    public void setHttpRequestUri(UriParameter httpRequestUri);
    public void setHttpRequestContent(Object requestMessage);
    public TestAutomationHttpResponse sendRequest();
    public Object parseResponse(TestAutomationHttpResponse response) throws IOException;
}
