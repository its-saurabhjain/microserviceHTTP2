package com.example.microservice.currencyfrontendservice;

import java.util.Collections;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

 
@Configuration
public class RestTemplateConfig {
 
    @Autowired
    CloseableHttpClient httpClient;
 
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        return restTemplate;
    }
    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        return clientHttpRequestFactory;
    }
    
    //@Bean
    public WebClient webClient() {
    WebClient client3 = WebClient
    		  .builder()
    		    .baseUrl("https://localhost:8443")
    		    .defaultCookie("cookieKey", "cookieValue")
    		    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
    		    .defaultUriVariables(Collections.singletonMap("url", "https://localhost:8443"))
    		  .build();
    return client3;
    }
    @Bean
    public WebClient webClient2() {
    WebClient client3 = WebClient
    		  .builder()
    		    .defaultCookie("cookieKey", "cookieValue")
    		    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
    		    .defaultUriVariables(Collections.singletonMap("url", "http://localhost:5080"))
    		  .build();
    return client3;
    }

    
}
