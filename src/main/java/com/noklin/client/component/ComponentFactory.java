package com.noklin.client.component;

import com.noklin.client.Log;
import com.noklin.client.util.Json;

public enum ComponentFactory {
	INSTANCE;
	
	/*
	 * 
	 * 
	 * */
	
	public Component create(String json) {
		ComponentConfig config = new ComponentConfig(Json.asJSONValueMap(json));
		Component component = craete(config.getString("type"), config);
		component.configurate(); 
		return component;
	}
	
	private Component craete(String typeName, ComponentConfig config) {
		switch(typeName) {
			case "html": return new Html(config);
			default:{
				Log.warn("Component type is not found");
				return Component.NULL;
			}
		}
	}
	
}