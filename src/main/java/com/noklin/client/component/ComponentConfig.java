package com.noklin.client.component;

import java.util.Map;
import java.util.function.BiConsumer;

import com.google.gwt.json.client.JSONValue;

public class ComponentConfig{
	private final Map<String,JSONValue> config;
	public ComponentConfig(Map<String, JSONValue> config) {
		this.config = config;
	}

	public String getString(String value) {
		return JsonUtil.asString(config.get(value));
	}
	
	public void forEach(BiConsumer<String,JSONValue> consumer) {
		config.forEach(consumer);
	}
	
}