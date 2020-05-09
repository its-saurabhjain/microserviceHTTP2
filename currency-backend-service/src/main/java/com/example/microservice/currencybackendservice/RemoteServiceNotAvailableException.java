package com.example.microservice.currencybackendservice;

public class RemoteServiceNotAvailableException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String _message = null;
	RemoteServiceNotAvailableException(String message){
		this._message = message;
	}

}
