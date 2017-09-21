/**
 * 
 */
package org.appdot.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 使用Jackson将Object转化为JSON形式存储的字符串
 * 
 * @author liand
 *
 */
public class JSONUtils {

	private static ObjectMapper mapper = new ObjectMapper();

	public static String toJSONStyle(Object obj) throws IOException {
		return mapper.writeValueAsString(obj);
	}

	public static <T> T valueOf(String jsonStr, Class<T> clazz) throws IOException {
		return mapper.readValue(jsonStr, clazz);
	}

	public static <T> List<T> getList(String jsonStr, TypeReference<List<T>> type) throws IOException {
		return mapper.readValue(jsonStr, type);
	}

	public static <K, T> Map<K, T> getMap(String jsonStr, TypeReference<Map<K, T>> type) throws IOException {
		return mapper.readValue(jsonStr, type);
	}

}
