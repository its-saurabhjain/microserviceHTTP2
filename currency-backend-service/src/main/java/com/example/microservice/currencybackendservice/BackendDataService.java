package com.example.microservice.currencybackendservice;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

public interface BackendDataService {
	
    @Retryable(value = { RemoteServiceNotAvailableException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public String getHistoricData() throws RemoteServiceNotAvailableException;

    @Recover
    public String getBackendResponseFallback(RuntimeException e);
}