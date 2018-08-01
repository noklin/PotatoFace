package com.noklin.client.util;

import com.google.gwt.user.client.Window;

public class Resource {

	public static String getConfigLocatio() {
		String path = Window.Location.getPath();
		path = path.endsWith("/") ? path : path + "/";
		return path + "comp/config.json";
	}

	public static String getRootComponentLocation() {
		String path = Window.Location.getPath();
		path = path.endsWith("/") ? path : path + "/";
		return path + "comp/root.json";
	}

	public static String getResourcePath() {
		String path = Window.Location.getPath();
		path = path.endsWith("/") ? path : path + "/";
		return path + "comp/";
	}
}
