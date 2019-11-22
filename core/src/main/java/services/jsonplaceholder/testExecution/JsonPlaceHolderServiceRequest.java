package services.jsonplaceholder.testExecution;

import org.springframework.stereotype.Component;
import services.handler.TestAutomationServiceMessage;
import services.jsonplaceholder.JsonPlaceHolderService;

@Component
public class JsonPlaceHolderServiceRequest {
    private JsonPlaceHolderService service;
    private TestAutomationServiceMessage message;

    public void sendMessage() {
        service = new JsonPlaceHolderService();
        service.setRequestMessage(message);
        service.sendRequest();
    }

    public JsonPlaceHolderService getService() {
        return service;
    }

    public void setService(JsonPlaceHolderService service) {
        this.service = service;
    }

    public TestAutomationServiceMessage getMessage() {
        return message;
    }

    public void setMessage(TestAutomationServiceMessage message) {
        this.message = message;
    }
}
