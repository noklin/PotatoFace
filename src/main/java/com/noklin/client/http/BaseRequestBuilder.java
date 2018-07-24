package com.noklin.client.http;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.noklin.client.App;
import com.noklin.client.Log;

public class BaseRequestBuilder<T> extends RequestBuilder implements RequestCallback{

	private final Function<String,T> converter;
	private final Map<Integer,Consumer<T>> responceCallbacks = new HashMap<>();
	
	public BaseRequestBuilder(Method httpMethod, String url, Function<String,T> converter) {
		super(httpMethod, url);
		this.converter = converter;
		setCallback(this);
	}

	@Override
	public void onResponseReceived(Request request, Response response) {
		int statusCode = response.getStatusCode();
		Consumer<T> consumer = responceCallbacks.get(statusCode);
		if(consumer == null) {
			switch(statusCode){
				case 500:
					Log.error("Iternal server error: " + response.getText());
					break; 
				case 401:
					App.INSTANCE.moveToLoginPage();
					break; 
				default:
					Log.warn("Ignored response: " + response); 
			}
		}else{
			consumer.accept(converter.apply(response.getText()));
		}
	}
	
	@Override
	public Request send(){
		try {
			super.send();
		}catch(RequestException ex){
			Log.error("Request failed: " + ex.getMessage());
		}
		return null;
	}
	
	@Override
	public void onError(Request request, Throwable exception) {
		Log.error("Request failed: " + request);
	}
	
	public void onStatusCode(int statusCode, Consumer<T> comsumer) {
		responceCallbacks.put(statusCode, comsumer);
	}
	
}