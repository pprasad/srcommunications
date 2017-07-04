package com.srm.services.dto;

import java.io.Serializable;

public class Response<T> implements Serializable {
    private String result;
    private T response;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
     
}
