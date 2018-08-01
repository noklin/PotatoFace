package com.noklin.client;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.noklin.client.http.ConfigRequest;

public enum Config {
	INSTANCE;
	private Map<String,String> config;
	private boolean loaded;

	public String getComponentLocation(Consumer<String> consumer) {
		return getProperty("compLocation");
	}
	
	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	private String getProperty(String key) {
		if(loaded) {
			return config.get(key);
		}else {
			throw new IllegalStateException("Config not loaded");
		}
	}
	
	public String getLogLevel() {
		return getProperty("logLevel");
	}

	public String getAuthGate() {
		return getProperty("authGate");
	}
	
	public void load(Runnable afterLoadAction) {
		ConfigRequest configRequest = new ConfigRequest();
		configRequest.onStatusCode(200, map -> {
			config = new HashMap<>(map);
			afterLoadAction.run();
		});
		configRequest.send(); 
	}
	
	@Override
	public String toString() {
		return "Config: " + config;
	}
	
}