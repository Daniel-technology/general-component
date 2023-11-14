package com.general.component.common.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: Jackson工具
 * @author: liuyan
 * @create: 2022−10-21 10:23 AM
 */
public class JacksonSupport {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            //设置null值不参与序列化(字段不被显示)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            //设置在反序列化时忽略在JSON字符串中存在，而在Java中不存在的属性
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    /**
     * obj转换json
     *
     * @param obj obj
     * @return json
     * @throws Exception
     */
    public static String objToJsonStr(Object obj) throws Exception {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 判断json 是否合规
     *
     * @param json
     * @return
     * @throws IOException
     */
    public static boolean isValidJson(final String json) throws IOException {
        boolean valid = false;
        try {
            JsonParser parser = MAPPER.getFactory()
                    .createParser(json);
            while (parser.nextToken() != null) {
            }
            valid = true;
        } catch (Exception e) {
            throw e;
        }
        return valid;
    }

    /**
     * map转对象
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T mapToObj(Map<String, Object> map, Class<T> clazz) {
        return MAPPER.convertValue(map, clazz);
    }


    /**
     * str转对象
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T strToObj(String str, Class<T> clazz) {
        return MAPPER.convertValue(str, clazz);
    }

    /**
     * obj 转 map
     *
     * @param obj obj
     * @return Map
     * @throws Exception e
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> objToMap(Object obj) throws Exception {
        return MAPPER.readValue(MAPPER.writeValueAsString(obj), HashMap.class);
    }

    /**
     * str 转 map
     *
     * @param str obj
     * @return Map
     * @throws Exception e
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> strToMap(String str) throws Exception {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return MAPPER.readValue(str, HashMap.class);
    }


}
