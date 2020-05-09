package com.example.microservice.currencyfrontendservice;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http2.Http2Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class CurrencyFrontEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyFrontEndApplication.class, args);
        
    }
	@Bean
	public ServletWebServerFactory servletContainer1() {
	    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
	    tomcat.addAdditionalTomcatConnectors(createH2cConnector());
	    return tomcat;
	}
	//to enable h2c port
	private Connector createH2cConnector() {
	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	    Http2Protocol upgradeProtocol = new Http2Protocol();
	    connector.addUpgradeProtocol(upgradeProtocol);
	    connector.setScheme("http");
	    connector.setSecure(false);
	    connector.setPort(8081);
	    return connector;
	}
}
