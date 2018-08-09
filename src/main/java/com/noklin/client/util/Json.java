package com.noklin.client.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class Json {
	public Json(String text) {
		if (text == null) {
			jsonValue = null;
		} else {
			try {
				jsonValue = JSONParser.parseStrict(text);
			} catch (IllegalArgumentException ex) {
				jsonValue = null;
			}
		}
	}

	private JSONValue jsonValue;

	public Json(JSONValue jsonValue) {
		this.jsonValue = jsonValue;
	}

	public Json getJson(String key) {
		Json result = NULL;
		if (jsonValue != null) {
			JSONObject jobj = jsonValue.isObject();
			if (jobj != null) {
				result = new Json(jobj.get(key));
			}
		}
		return result;
	}

	/**
	 * Returns a String value of this Json or "" if it's null value. 
	 * For check use <code>isNull()</code>
	 * 
	 * @return a String presentation of this json value or "" if it's null value.
	 */
	public String asString() {
		return asStringOrDefault("");
	}

	public String asStringOrDefault(String defaultValue) {
		String result = defaultValue;
		if (jsonValue != null) {
			JSONString jString = jsonValue.isString();
			if (jString != null) {
				result = jString.stringValue();
			}
		}
		return result;
	}
	
	/**
	 * Returns a int value of this Json or 0 if it's null value. 
	 * For check use <code>isNull()</code>
	 * 
	 * @return a int presentation of this json value or 0 if it's null value.
	 */
	public int asInt() {
		return asInt(0);
	}
	
	public int asInt(int valueIfNotExist) {
		int result = valueIfNotExist;
		if (jsonValue != null) {
			JSONNumber jNum = jsonValue.isNumber();
			if (jNum != null) {
				result = (int) jNum.doubleValue();
			}
		}
		return result;
	}

	/**
	 * Returns a boolean value of this Json or false if it's null value. 
	 * For check use <code>isNull()</code>
	 * 
	 * @return a boolean presentation of this json value or false if it's null value.
	 */
	public boolean asBool() {
		return asBool(false);
	}
	
	public boolean asBool(boolean valueIfNotExist) {
		boolean result = valueIfNotExist;
		if (jsonValue != null) {
			JSONBoolean jBool = jsonValue.isBoolean();
			if (jBool != null) {
				result = jBool.booleanValue();
			}
		}
		return result;
	}

	public Map<String, String> asStringMap() {
		return asSpecialMap(jsonValue -> {
			return jsonValue.isString().stringValue();
		}, jVal -> jVal.isString() != null) ;
	}

	public Map<String, Json> asMap() {
		return asSpecialMap(Json::new, t -> true);
	}
	
	private <T> Map<String, T> asSpecialMap(Function<JSONValue, T> converter, Predicate<JSONValue> existPredicate) {
		Map<String, T> result = Collections.emptyMap();
		if (jsonValue != null) {
			JSONObject jObj = jsonValue.isObject();
			if (jObj != null) {
				Set<String> keys = jObj.keySet();
				if (!keys.isEmpty()) {
					result = new HashMap<>();
					for (String k : keys) {
						JSONValue jVal = jObj.get(k);
						if(existPredicate.test(jVal)){
							result.put(k, converter.apply(jVal));
						}
					}
				}
			}
		}
		return result;
	}
	

	public List<Json> asList() {
		List<Json> result = Collections.emptyList();
		if (jsonValue != null) {
			JSONArray jArr = jsonValue.isArray();
			if (jArr != null) {
				int size = jArr.size();
				if (size != 0) {
					result = new ArrayList<>(size);
					for (int i = 0; i < size; i++) {
						result.add(new Json(jArr.get(i)));
					}
				}
			}
		}
		return result;
	}

	public boolean isNull() {	
		return jsonValue == null;
	}

	public final static Json NULL = new Json((JSONValue) null);
	
	@Override
	public String toString() {
		return jsonValue == null ? "null" : jsonValue.toString();
	}

}