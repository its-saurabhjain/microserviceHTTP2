package com.example.microservice.currencyfrontendservice;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;


@Log4j2
@ContextConfiguration(classes = {RestTemplateConfig.class, HttpClientConfig.class})
@RestController
public class CurrencyFrontEndController {

    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    WebClient webClient;

    @Value("${data.service.url}")
    private String DATA_SERVICE_URL;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/currencyconverter/from/{from}/to/{to}/quantity/{quantity}")
	public String convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable double quantity) {
		String response="";
		//String DATA_SERVICE_URL1 = "https://backendsvc." + System.getenv("POD_NAMESPACE") + ".svc:6443/currency-backend";
		response= requestProcessedData(DATA_SERVICE_URL);
		
		if(!response.equalsIgnoreCase(null) || response != "") {
		    JSONObject jsonObject = new JSONObject(response);
		    JSONObject objRates = jsonObject.getJSONObject("rates");
		    String fromCurr = objRates.get(from.toUpperCase()).toString();
		    String toCurr = objRates.get(to.toUpperCase()).toString();
		    Double amount = quantity * getRates(fromCurr,toCurr);
		    JSONObject result = new JSONObject();
		    result.put("from", from);
		    result.put("to", to);
		    result.put("quantity", quantity);
		    result.put("rate", getRates(fromCurr,toCurr));
		    result.put("amount", amount);
		    response = result.toString();
		}
	return response;
	}
	
    //Using blocking RestTemplate
	/*
    private String requestProcessedData(String url) {
        String result = restTemplate.getForObject(url, String.class);
        return (result);
    }*/
    //ssl connection using new Spring Recative webclient approach
	
    private String requestProcessedData(String url) {
    	WebClient client = WebClient.create(url);
    	Mono<ClientResponse> result = client.get().exchange();
    	return result.flatMap(res -> res.bodyToMono(String.class)).block();
    }
    private double getRates(String fromCurr, String toCurr) {
		Double rate= Double.parseDouble(toCurr) / Double.parseDouble(fromCurr);
		return rate;
		
	}
}
