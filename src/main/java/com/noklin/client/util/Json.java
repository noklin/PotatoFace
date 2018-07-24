package com.noklin.client.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class Json {
	
	public static <T> Map<String, T> asMap(JSONObject jsonObject, Predicate<JSONValue> existPredicate
			, Function<JSONValue,T> converter, Supplier<Map<String, T>> sup){
		
		if(jsonObject == null) {
			return Collections.<String,T>emptyMap();
		}
		Set<String> keySet = jsonObject.keySet();
		Map<String, T> result = sup.get(); 
		keySet.forEach(k -> {
			JSONValue val = jsonObject.get(k);
			if(existPredicate.test(val)) {
				result.put(k, converter.apply(val));
			}
		});
		return result;
	}

	public static Map<String, String> asStringMap(JSONObject jsonObject){
		return Json.<String>asMap(jsonObject, jv->jv.isString() != null, Json::asString, HashMap::new);
	}

	public static Map<String, String> asStringMap(String jsonText){
		return asStringMap(JSONParser.parseStrict(jsonText).isObject());
	}

	public static Map<String, JSONValue> asJSONValueMap(JSONObject jsonObject){
		return Json.<JSONValue>asMap(jsonObject, t->true, f->f, HashMap::new);
	}

	public static Map<String, JSONValue> asJSONValueMap(String json){
		return asJSONValueMap(JSONParser.parseStrict(json).isObject());
	}

	public static Map<String, JSONValue> asConfig(String json){
		return asJSONValueMap(JSONParser.parseStrict(json).isObject());
	}
	
	public static int asInt(JSONValue v, int defaultValue) {
		JSONNumber nVal = v.isNumber();
		return nVal == null ? defaultValue : (int)nVal.doubleValue();
	}

	public static int asInt(JSONValue v) {
		return asInt(v, 0);
	}

	public static String asString(JSONValue v, String defaultValue) {
		JSONString sVal = v.isString();
		return sVal == null ? defaultValue : sVal.stringValue();
	}
	
	public static String asString(JSONValue v) {
		return asString(v, "");
	}
	
	public static JSONObject asJSONObject(JSONValue v) {
		return asJSONObject(v, new JSONObject());
	}

	public static JSONObject asJSONObject(JSONValue v, JSONObject defaultValue) {
		return new JSONObject();
	}
}
