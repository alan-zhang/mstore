package org.azt.mstore.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class JSONUtil {

	@SuppressWarnings("rawtypes")
	public static <T> T readToObject(String json, Class clz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		@SuppressWarnings("unchecked")
		T obj = (T)objectMapper.readValue(json, clz);
		
		return obj;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T readToObject(InputStream inputStream, Class clz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		return (T)objectMapper.readValue(inputStream, clz);
	}
	
	public static <T> List<T> readToList(InputStream inputStream, Class clz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JavaType valueType = mapper.getTypeFactory().constructCollectionType(List.class, clz);
		
		return mapper.readValue(inputStream, valueType);
	}
}
