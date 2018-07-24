package com.noklin.client.component;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

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
			case "name" : 
				setAttribute("name", JsonUtil.asString(v));
				break;
			}
		});
	}
	
	private void setAttribute(String name, String value) {
		asWidget().getElement().setAttribute(name, value);
	}
	
	public static final Component NULL = new Component(null){
		private final HTML widget = new HTML("<div>Empty component</div>");
		@Override
		public Widget asWidget() {
			return widget;
		}
		public boolean isNullComponent() {
			return true;
		}
	};
	
}