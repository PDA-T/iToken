package com.pda.itoken.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jackson 工具类
 */
public class MapperUtils {
	private final static ObjectMapper objectMapper = new ObjectMapper();

	public static ObjectMapper getInstance(){
		return objectMapper;
	}

	/**
	 * 转换为JSON字符串
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String obj2json(Object obj) throws Exception{
		return objectMapper.writeValueAsString(obj);
	}

	/**
	 * 转换为JSON字符串,忽略空值
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String obj2jsonIgnoreNull(Object obj) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsString(obj);
	}

	/**
	 * 转换为javaBean
	 * @param jsonString
	 * @param clazz
	 * @return
	 * @param <T>
	 * @throws Exception
	 */
	public static <T> T json2pojo(String jsonString,Class<T> clazz) throws Exception{
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
		return objectMapper.readValue(jsonString,clazz);
	}

	/**
	 * 字符串转换为Map<String,Object>
	 * @param jsonString
	 * @return
	 * @param <T>
	 * @throws Exception
	 */
	public static <T> Map<String,Object> json2map(String jsonString) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.readValue(jsonString,Map.class);
	}

	/**
	 * 字符串转换为Map<String,T>
	 * @param jsonString
	 * @param clazz
	 * @return
	 * @param <T>
	 * @throws Exception
	 */
	public static <T> Map<String,T> json2map(String jsonString,Class<T> clazz) throws Exception{
		Map<String,Map<String,Object>> map = objectMapper.readValue(jsonString,new TypeReference<Map<String,T>>(){});
		Map<String,T> result = new HashMap<String,T>();
		for (Map.Entry<String,Map<String,Object>> entry:map.entrySet()){
			result.put(entry.getKey(),map2pojo(entry.getValue(),clazz));
		}
		return result;
	}

	/**
	 * 深度转换JSON成Map
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> json2mapDeeply(String json) throws Exception{
		return json2MapRecursion(json,objectMapper);
	}

	/**
	 * 把JSON解析成List,如果List内部的元素存在jsonString,继续解析
	 * @param json
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	private static List<Object> json2ListRecursion(String json,ObjectMapper mapper) throws Exception{
		if (json == null){
			return null;
		}

		List<Object> list = mapper.readValue(json,List.class);

		for (Object obj:list){
			if (obj != null && obj instanceof String){
				String str = (String) obj;
				if (str.startsWith("[")){
					obj = json2ListRecursion(str,mapper);
				}else if (obj.toString().startsWith("{")){
					obj = json2MapRecursion(str,mapper);
				}
			}
		}
		return list;
	}

	/**
	 * 把JSON解析成Map,如果Map内部的Value存在jsonString,继续解析
	 * @param json
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	private static Map<String,Object> json2MapRecursion(String json,ObjectMapper mapper) throws Exception{
		if (json == null){
			return null;
		}

		Map<String,Object> map = mapper.readValue(json,Map.class);

		for (Map.Entry<String,Object> entry:map.entrySet()){
			Object obj = entry.getValue();
			if (obj != null && obj instanceof String){
				String str = ((String) obj);
				if (str.startsWith("[")){
					List<?> list = json2ListRecursion(str,mapper);
					map.put(entry.getKey(),list);
				}else if (str.startsWith("{")){
					Map<String,Object> mapRecursion = json2MapRecursion(str,mapper);
					map.put(entry.getKey(),mapRecursion);
				}
			}
		}
		return map;
	}

	/**
	 * 将JSON数组转换为集合
	 * @param jsonArrayStr
	 * @param clazz
	 * @return
	 * @param <T>
	 * @throws Exception
	 */
	public static <T> List<T> json2list(String jsonArrayStr,Class<T> clazz) throws Exception{
		JavaType javaType = getCollectionType(ArrayList.class,clazz);
		List<T> list = (List<T>) objectMapper.readValue(jsonArrayStr,javaType);
		return list;
	}

	/**
	 * 获取泛型的Collection Type
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 */
	public static JavaType getCollectionType(Class<?> collectionClass,Class<?>... elementClasses){
		return objectMapper.getTypeFactory().constructParametricType(collectionClass,elementClasses);
	}

	/**
	 * 将Map转换为JavaBean
	 * @param map
	 * @param clazz
	 * @return
	 * @param <T>
	 */
	public static <T> T map2pojo(Map map,Class<T> clazz){
		return objectMapper.convertValue(map,clazz);
	}

	/**
	 * 将Map转换为JSON
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map map){
		try {
			return objectMapper.writeValueAsString(map);
		}catch (Exception e){
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 将JSON对象转换为JavaBean
	 * @param obj
	 * @param clazz
	 * @return
	 * @param <T>
	 */
	public static <T> T obj2pojo(Object obj,Class<T> clazz){
		return objectMapper.convertValue(obj,clazz);
	}
}
