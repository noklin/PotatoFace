package com.noklin.client.component;

import com.noklin.client.Log;
import com.noklin.client.util.Json;

public enum ComponentFactory {
	INSTANCE;
	
	/*
	 * 
	 * 
	 * */
	
	public Component create(String jsonText) {
		Json jSon = new Json(jsonText); 
		Json componentType = jSon.getJson("type");
		if(componentType.isNull()) {
			Log.error("Failed create component. Component type is null or not exist. " + jsonText);
		}
		Component component = craete(componentType.asString(), jSon);
		component.configurate(); 
		return component;
	}
	
	private Component craete(String typeName, Json config) {
		switch(typeName) {
			case "html": return new Html(config);
			default:{
				Log.warn("Component type is not found");
				return Component.NULL;
			}
		}
	}
	
}