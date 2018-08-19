package com.noklin.client;

import com.google.gwt.user.client.Window;

public enum App {
	INSTANCE;
	
	public void moveToLoginPage() {
		Log.info("Window.Location.getPath() + \"/login\" " + Window.Location.getPath() + "/login");
		Window.Location.assign("login");
	}
}
