package services.handler;

import java.io.IOException;

public interface TestAutomationService {
    public Object getParsedResponse() throws IOException;
    public TestAutomationHttpResponse getRawResponse();
    public void sendRequest();
    public void setRequestMessage(TestAutomationServiceMessage message);
}
