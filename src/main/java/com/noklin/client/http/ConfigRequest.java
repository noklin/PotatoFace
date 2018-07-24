package com.noklin.client.http;

import java.util.Map;

import com.google.gwt.http.client.RequestBuilder;
import com.noklin.client.util.Json;
import com.noklin.client.util.Resource;

public class ConfigRequest extends BaseRequestBuilder<Map<String,String>>{
	
	public ConfigRequest(String url) {
		super(RequestBuilder.GET, url, Json::asStringMap);
	}

	public ConfigRequest() {
		this(Resource.getConfigUri());
	}

}