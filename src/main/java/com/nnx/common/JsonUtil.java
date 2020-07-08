package com.nnx.common;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The json utility class will have methods to convert json content.
 * 
 * @author hafeez
 */
public final class JsonUtil {

    private JsonUtil() {
    }

    public static <T> T convertJSONToObject(String jsonContent, Class<T> valueType) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonContent, valueType);
    }

    public static String convertObjectToJSON(Object object) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
