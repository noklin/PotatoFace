package com.noklin.client.component;

import java.util.Collections;
import java.util.Map;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.noklin.client.http.TextRequest;
import com.noklin.client.util.Json;
import com.noklin.client.util.Resource;

public abstract class Component implements IsWidget{
	private final ComponentConfig config;
	Component(ComponentConfig config){
		this.config = config;
	}
	
	void afterCreate() {
	}

	void add(Component child) {
		throw new UnsupportedOperationException();
	}
	
	public Field asField() {
		throw new UnsupportedOperationException();
	}
	
	public String getName() {
		throw new UnsupportedOperationException();
	}
	
	public String getConfigPropertyAsString(String name){
		throw new UnsupportedOperationException();
	}
	
	public int getConfigPropertyAsInt(String name){
		throw new UnsupportedOperationException();
	}
	
	public boolean getConfigPropertyAsBool(String name){
		throw new UnsupportedOperationException();
	}
	
	
	public ComplexComponent asComplex() {
		return null;
	}
	
	public boolean isNullComponent() {
		return false;
	}
	
	final void configurate() {
		config.forEach((k,v) -> {
			switch(k){
			case "scope" : break;
			case "att" : 
				JSONObject jObj = v.isObject();
				if(jObj != null) {
					Map<String, String> att = Json.asStringMap(jObj);
					att.forEach(this::setAttribute);
				}
				break;
			case "htmlSource" : 
				break;
			case "name" : 
				setAttribute("name", Json.asString(v));
				break;
			}
		});
		
	}
	
	private void setAttribute(String name, String value) {
		asWidget().getElement().setAttribute(name, value);
	}
	
	public static final Component NULL = new Component(new ComponentConfig(Collections.emptyMap())){
		private final HTML widget = new HTML("<div>Unknown component</div>");
		@Override
		public Widget asWidget() {
			return widget;
		}
		public boolean isNullComponent() {
			return true;
		}
	};
	
}