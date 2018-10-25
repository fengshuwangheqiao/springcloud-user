package com.user.common.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author xuwenqian
 */
public class GsonUtil {
	private static final Logger log = LoggerFactory.getLogger(GsonUtil.class);
	private static Gson gson = null;
	
	private static JsonParser parser = null;
	
	static{
		gson  = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();
		parser = new JsonParser();
	}
	
	public static Gson getGson(){
		if(gson == null){
			gson =  new GsonBuilder()
					.setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
		}
		return gson;
	}
	
	public static JsonParser getParser(){
		if(parser == null){
			parser =  new JsonParser();
		}
		return parser;
	}
	
	public static String toJson(Object obj){
		return gson.toJson(obj);
	}
	
	public static <T> T toBean(String json,Class<T> clz){
		
		return gson.fromJson(json, clz);
	}
	
	public static <T> Map<String, T> toMap(String json,Class<T> clz){
		 Map<String, JsonObject> map = gson.fromJson(json, new TypeToken<Map<String,JsonObject>>(){}.getType());
		 Map<String, T> result = new HashMap<>();
		 for(String key:map.keySet()){
			 result.put(key,gson.fromJson(map.get(key),clz) );
		 }
		 return result;
	}
	
	public static Map<String, Object> toMap(String json){
		 Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String,Object>>(){}.getType());
		 return map;
	}
	
	public static <T> List<T> toList(String json,Class<T> clz){
		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
		List<T> list  = new ArrayList<>();
		for(final JsonElement elem : array){
	         list.add(gson.fromJson(elem, clz));
	    }
	    return list;
	}
	
	public static <T> Set<T> toSet(String json,Class<T> clz){
		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
		Set<T> set  = new HashSet<T>();
		for(final JsonElement elem : array){
			set.add(gson.fromJson(elem, clz));
	    }
	    return set;
	}

	public static <T> List<T> fromJsonArray(String json, Class<T> clazz) throws Exception {
		List<T> lst = new ArrayList<T>();
		JsonArray array = GsonUtil.getParser().parse(json).getAsJsonArray();
		for (final JsonElement elem : array) {
			lst.add(new Gson().fromJson(elem, clazz));
		}
		return lst;
	}

	public static <T> List<T> parseStringList(String json,Class clazz) {
		Type type = new ParameterizedTypeImpl(clazz);
		List<T> list =  new Gson().fromJson(json, type);
		return list;
	}


	private static class ParameterizedTypeImpl implements ParameterizedType {
		Class clazz;

		public ParameterizedTypeImpl(Class clz) {
			clazz = clz;
		}

		@Override
		public Type[] getActualTypeArguments() {
			return new Type[]{clazz};
		}

		@Override
		public Type getRawType() {
			return List.class;
		}

		@Override
		public Type getOwnerType() {
			return null;
		}
	}


	/**
	 * 判断是否为正确的json
	 * @param json
	 * @return
	 */
	public static boolean isGoodJson(String json) {
		if (StringUtils.isBlank(json)) {
			return false;
		}
		try {
			new JsonParser().parse(json);
			return true;
		} catch (JsonParseException e) {
			log.error("bad json: " + json);
			return false;
		}
	}
}
