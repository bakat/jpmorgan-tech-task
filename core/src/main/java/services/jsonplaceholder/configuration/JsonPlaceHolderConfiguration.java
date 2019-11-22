package services.jsonplaceholder.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import services.handler.HttpRequestHandler;

@Configuration
@ComponentScan(basePackages = {
        "services.handler"
})
public class JsonPlaceHolderConfiguration {

    @Bean
    public HttpRequestHandler getHttpRequestHandler(){
        return new HttpRequestHandler();
    }
}
