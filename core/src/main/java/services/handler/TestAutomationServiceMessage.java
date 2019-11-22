package services.handler;

import services.jsonplaceholder.domain.UriParameter;

public class TestAutomationServiceMessage {
    private Object content;
    private HttpRequestType httpRequestType;
    private UriParameter uriParameter;

    public TestAutomationServiceMessage(Object content, HttpRequestType httpRequestType, UriParameter httpRequestUri) {
        this.content = content;
        this.httpRequestType = httpRequestType;

        if(httpRequestUri != null){
            this.uriParameter = httpRequestUri;
        }
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public HttpRequestType getHttpRequestType() {
        return httpRequestType;
    }

    public void setHttpRequestType(HttpRequestType httpRequestType) {
        this.httpRequestType = httpRequestType;
    }

    public UriParameter getUriParameter() {
        return uriParameter;
    }
}
