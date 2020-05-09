package com.example.microservice.currencybackendservice;

import java.io.IOException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class BackendDataRepository {

    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${app.service.exchangeserviceURL}")
    private String ServiceURL;
    @Value("${app.service.exchangeserviceURL}")
    private String timeOut;

    public String getHistoricData() {
        String exchangeUrl = ServiceURL;
        String response = "Test timeout";
        try {
        		response = requestProcessedData(exchangeUrl);
        		long t = 2000L;
        		if(timeOut == "true")
        			Thread.sleep(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return response;
    }
    
    private String requestProcessedData(String url) {
        String result = restTemplate.getForObject(url, String.class);
        return (result);
    }
    /*
    private String requestProcessedData(String url1) {
        String output = null;
        try {
          		OkHttpClient client = new OkHttpClient();
          		HttpUrl.Builder urlBuilder  = HttpUrl.parse(url1).newBuilder();
          		String url = urlBuilder.build().toString();
          		Request request = new Request.Builder()
          				.url(url)
          				.build();
          		Call call = client.newCall(request);
          		Response response = call.execute();
          		System.out.println(response.protocol());
          		System.out.println(response.body().string());
          		output = response.body().string();
   			} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   			}
   		return output;
     }*/
}
