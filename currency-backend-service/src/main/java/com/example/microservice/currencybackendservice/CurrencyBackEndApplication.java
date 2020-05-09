package com.example.microservice.currencybackendservice;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http2.Http2Protocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;


@EnableRetry
@SpringBootApplication
public class CurrencyBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyBackEndApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	//enable redirection from http to https
	//@Bean
	public ServletWebServerFactory servletContainer() {
	    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	        @Override
	        protected void postProcessContext(Context context) {
	            SecurityConstraint securityConstraint = new SecurityConstraint();
	            securityConstraint.setUserConstraint("CONFIDENTIAL");
	            SecurityCollection collection = new SecurityCollection();
	            collection.addPattern("/*");
	            securityConstraint.addCollection(collection);
	            context.addConstraint(securityConstraint);
	        }
	    };
	    tomcat.addAdditionalTomcatConnectors(redirectConnector());
	    return tomcat;
	}

	private Connector redirectConnector() {
	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	    connector.setScheme("http");
	    connector.setPort(8000);
	    connector.setSecure(false);
	    connector.setRedirectPort(8443);
	    return connector;
	}
    //creating h2c version
	@Bean
	public ServletWebServerFactory servletContainer1() {
	    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
	    tomcat.addAdditionalTomcatConnectors(createH2cConnector());
	    return tomcat;
	}
	private Connector createH2cConnector() {
	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	    Http2Protocol upgradeProtocol = new Http2Protocol();
	    connector.addUpgradeProtocol(upgradeProtocol);
	    connector.setScheme("http");
	    connector.setSecure(false);
	    connector.setPort(5080);
	    return connector;
	}
	//@Bean
	public ServletWebServerFactory servletContainer2() {
	    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
	    tomcat.addAdditionalTomcatConnectors(createH2Connector());
	    return tomcat;
	}
	private Connector createH2Connector() {
	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	    Http2Protocol upgradeProtocol = new Http2Protocol();
	    connector.addUpgradeProtocol(upgradeProtocol);
	    connector.setScheme("https");
	    connector.setSecure(true);
	    connector.setPort(5081);
	    return connector;
	}
}
