package com.noklin.client;

import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.noklin.client.http.ComponentRequest;
import com.noklin.client.util.Json;
import com.noklin.client.util.Resource; 

public class PotatoFace implements EntryPoint {
	
	private void onConfigLoaded() {
		Log.debug("" + Config.INSTANCE);
		
		Json json = Config.INSTANCE.getJson();
		
		Json sp = json.getJson("systemProperties");
		Map<String, Json> map = sp.asMap();
		
		parseProperty("{services.session.handler.asValue()}");
		
		ComponentRequest componentRequest = new ComponentRequest(Resource.getRootComponentLocation());
		componentRequest.onStatusCode(200, c->{
			RootPanel.get().add(c);
		});
		componentRequest.send();
	}
	
	public void onModuleLoad() {
		Config.INSTANCE.load(this::onConfigLoaded);
	}
	
	
	private String parseProperty(String v) {
		
		return "";
	}
}



/*
 *{
	"compLocation" : "http://127.0.0.1",
	"logLevel" : "DEBUG",
	"authGate" : "http://127.0.0.1/authGate",
	
	"systemProperties" : {
		"token" : "asValue{call{services.session}}"
	},
	
	"services" : {
		"session" : {
			"target" : "http://webapi/iam",
			"method" : "get",
			"handler" : "resource{js/handler.js}"
		} 
	}
} 
 * 
 * */
 