package com.noklin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.noklin.client.http.ConfigRequest;
import com.noklin.client.util.Resource; 

public class PotatoFace implements EntryPoint {
	public void onModuleLoad() {
		Log.debug("path: " + Resource.getConfigUri());
		
		ConfigRequest configRequest = new ConfigRequest();
		configRequest.onStatusCode(200, config -> {
			Log.info("app config: " + config);
		});
		configRequest.send();
		
//		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, detectResourceName()); 
//		try {
//			
//			rb.sendRequest(null, new RequestCallback() {
//				
//				@Override
//				public void onResponseReceived(Request request, Response response) {
//					ComponentFactory fsctory = new ComponentFactory();
//					Log.debug("" + response.getStatusCode());
//					Log.debug("" + response.getText());
//					long tb = System.currentTimeMillis();
//					RootPanel.get().add(fsctory.create(response.getText()).asWidget());
//					long ta = System.currentTimeMillis();
//					Log.debug("time: " + (ta-tb) + " ms");
//				}
//				
//				@Override
//				public void onError(Request request, Throwable exception) {
//				}
//				
//			});
//			
//		} catch (RequestException e) { 
//			e.printStackTrace();
//		} 
	}
	private String detectResourceName() {
		String resName = Window.Location.getPath();
		return "http://localhost/comp" + resName + "/root.json";
	}
}
