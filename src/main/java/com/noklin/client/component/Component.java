package com.noklin.client.component;

import java.util.Map;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.noklin.client.util.Json;

public abstract class Component implements IsWidget{
	

	
	private final Json config;
	Component(Json config){
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
		config.asMap().forEach((k,v) -> {
			switch(k){
			case "scope" : break;
			case "att" : 
				Map<String, String> att = v.getJson("att").asStringMap();
				att.forEach(this::setAttribute);
				break;
			case "htmlSource" : 
				break;
			case "name" : 
				setAttribute("name", v.asStringOrDefault("unknown"));
				break;
			}
		});
		
	}
	
	private void setAttribute(String name, String value) {
		asWidget().getElement().setAttribute(name, value);
	}
	
	private Component(){
		config = Json.NULL;
	}
	
	public static final Component NULL = new Component() {
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