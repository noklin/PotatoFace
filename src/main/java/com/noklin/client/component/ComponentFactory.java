package com.noklin.client.component;

import java.util.HashMap;
import java.util.Map;

import com.noklin.client.Log;

public class ComponentFactory {

	public Component create(String json) {
		ComponentConfig config = new ComponentConfig(JsonUtil.asJSONValueMap(json));
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
	
	public static class ComponentTemplate{
		final String name = "name";
		final Map<String,String> config = new HashMap<>();

		public String getConfigPropertyAsString(String name){
			throw new UnsupportedOperationException();
		}
		
		public int getConfigPropertyAsInt(String name){
			throw new UnsupportedOperationException();
		}
		
		public boolean getConfigPropertyAsBool(String name){
			throw new UnsupportedOperationException();
		}
	}
	
	
	
}