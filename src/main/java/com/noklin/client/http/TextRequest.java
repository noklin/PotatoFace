package com.noklin.client.http;

public class TextRequest extends BaseRequestBuilder<String>{
	public TextRequest(String url) {
		super(GET, url, t->t); 
	}
}