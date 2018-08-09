package com.noklin.client.util;

import java.util.Map;

import com.google.gwt.junit.client.GWTTestCase;

public class JsonTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "com.noklin.TestModule";
	}

	
	String simpleJsonText = "{\"int\" : 12, \"obj\" : {\"0\":\"0\"} , \"bool\" : true, \"arr\" : [{},{}], \"text\" : \"text\"}";
	
	public void testGetMapSize() {
		Json json = new Json(simpleJsonText); 
		Map<String, Json> map = json.asMap();
		assertTrue(map.size() == 5); 
	}

	public void testDefaultValueInt() {
		Json json = new Json(simpleJsonText); 
		assertTrue(json.getJson("int").asInt(4) == 12); 
		assertTrue(json.getJson("obj").asInt(4) == 4); 
		assertTrue(json.getJson("bool").asInt(4) == 4); 
		assertTrue(json.getJson("arr").asInt(4) == 4); 
		assertTrue(json.getJson("text").asInt(4) == 4); 
	}

	public void testDefaultValueBool() {
		Json json = new Json(simpleJsonText); 
		assertTrue(json.getJson("int").asBool(true)); 
		assertTrue(json.getJson("obj").asBool(true)); 
		assertTrue(!json.getJson("bool").asBool(false)); 
		assertTrue(json.getJson("arr").asBool(true));  
		assertTrue(json.getJson("text").asBool(true)); 
	}

	public void testDefaultValueString() {
		Json json = new Json(simpleJsonText); 
		assertTrue(json.getJson("int").asStringOrDefault("1") == "1"); 
		assertTrue(json.getJson("obj").asStringOrDefault("1") == "1"); 
		assertTrue(json.getJson("bool").asStringOrDefault("1") == "1"); 
		assertTrue(json.getJson("arr").asStringOrDefault("1") == "1"); 
		assertTrue(json.getJson("text").asStringOrDefault("1") == "text"); 
	}

	public void testNullValue() {
		Json json = new Json(simpleJsonText); 
		assertTrue(json.getJson("").isNull());  
	}

	public void testNotNullValue() {
		Json json = new Json(simpleJsonText); 
		assertTrue(!json.getJson("int").isNull());  
		assertTrue(!json.getJson("obj").isNull());  
		assertTrue(!json.getJson("bool").isNull());  
		assertTrue(!json.getJson("arr").isNull());  
		assertTrue(!json.getJson("text").isNull());  
	}

	public void testArrayValue() {
		Json json = new Json(simpleJsonText); 
		assertTrue(json.getJson("obj").asList().size() == 0);  
		assertTrue(json.getJson("int").asList().size() == 0);  
		assertTrue(json.getJson("bool").asList().size() == 0);  
		assertTrue(json.getJson("arr").asList().size() == 2);  
		assertTrue(json.getJson("text").asList().size() == 0);  
	}
}
