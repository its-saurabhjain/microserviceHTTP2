package com.example.microservice.currencybackendservice;

import java.time.LocalDate;
import java.util.Random;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Log4j2
public class CurrencyBackEndController implements BackendDataService {

	@Autowired
	private BackendDataRepository repository;
	
    @Value("${app.service.retry}")
    private String canRetry;
    @Value("${app.service.fallback}")
    private String canFallback;
	
	@Override
	@GetMapping("/currency-backend")
	public String getHistoricData() throws RemoteServiceNotAvailableException  {
		String exchangeValue = "";
		boolean retry = Boolean.valueOf(canRetry);
	    boolean isfallback = Boolean.valueOf(canFallback);
		exchangeValue = repository.getHistoricData();
		//If retry is set to true
        if (retry) {
            System.out.println("Retry is true, so try to simulate exception scenario.");
            if (isfallback) {
                throw new RemoteServiceNotAvailableException(
                        "Don't worry!! Just Simulated for Spring-retry..Must fallback as all retry will get exception!!!");
            }
            int random = new Random().nextInt(4);
            System.out.println("Random Number : " + random);
            if (random % 2 == 0) {
                throw new RemoteServiceNotAvailableException("Don't worry!! Just Simulated for Spring-retry..");
            }
        }
       return exchangeValue;
	}
	 @Override
	 public String getBackendResponseFallback(RuntimeException e) {
	        System.out.println("All retries completed, so Fallback method called!!!");
	        return "All retries completed, so Fallback method called!!!";
	    }

}
