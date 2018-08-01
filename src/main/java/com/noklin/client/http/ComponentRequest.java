package com.noklin.client.http;

import com.noklin.client.component.Component;
import com.noklin.client.component.ComponentFactory;

public class ComponentRequest extends BaseRequestBuilder<Component>{
	public ComponentRequest(String url) {
		super(GET, url, ComponentFactory.INSTANCE::create);
	}
}