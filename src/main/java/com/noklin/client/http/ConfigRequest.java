package com.noklin.client.http;

import com.google.gwt.http.client.RequestBuilder;
import com.noklin.client.util.Json;
import com.noklin.client.util.Resource;

public class ConfigRequest extends BaseRequestBuilder<Json>{
	
	public ConfigRequest(String url) {
		super(RequestBuilder.GET, url, Json::new);
	}

	public ConfigRequest() {
		this(Resource.getConfigLocatio());
	}

}