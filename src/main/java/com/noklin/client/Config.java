package com.noklin.client;

import com.noklin.client.http.ConfigRequest;
import com.noklin.client.util.Json;

public enum Config {
	INSTANCE;
	private Json config;
	private boolean loaded;

	
	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public String getLogLevel() {
		return "1";
	}

	public String getAuthGate() {
		return "s";
	}
	
	public void load(Runnable afterLoadAction) {
		ConfigRequest configRequest = new ConfigRequest();
		configRequest.onStatusCode(200, json -> {
			config = json;
			afterLoadAction.run();
		});
		configRequest.send(); 
	}
	
	@Override
	public String toString() {
		return "Config: " + config;
	}
	
	public Json getJson() {
		return config;
	}
	
}