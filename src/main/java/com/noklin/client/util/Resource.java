package com.noklin.client.util;

import com.google.gwt.user.client.Window;

public class Resource {

	public static String getConfigLocation() {
		return getPath() + "comp/config.json";
	}

	public static String getRootComponentLocation() {
		String sourceLocation = isLoginPage() ? "comp/login.json" : "comp/private/root.json"; 
		return getPath() + sourceLocation;
	}

	public static String getResourcePath() {
		return getPath() + "comp/";
	}
	
	private static String getPath() {
		String path = Window.Location.getPath();
		path = path.endsWith("/") ? path : path + "/";
		path = path.replace("/login/", "");	
		return path;	
	}
	
	private static boolean isLoginPage() {
		return Window.Location.getPath().contains("/login");
	}
	
}