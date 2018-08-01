package com.noklin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.noklin.client.http.ComponentRequest;
import com.noklin.client.util.Resource; 

public class PotatoFace implements EntryPoint {
	
	private void onConfigLoaded() {
		ComponentRequest componentRequest = new ComponentRequest(Resource.getRootComponentLocation());
		componentRequest.onStatusCode(200, c->{
			RootPanel.get().add(c);
		});
		componentRequest.send();
	}
	
	public void onModuleLoad() {
		Config.INSTANCE.load(this::onConfigLoaded);
	}
}
