package com.example.demo.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class JsonUtilsTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    void beanToJson() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        String result = JsonUtils.beanToJsonString(map, null);
        log.info(result);
    }

    @Test
    void beanToJson1() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        String result = JsonUtils.beanToJsonString(map);
        log.info(result);
    }

    @Test
    void beanToJson2() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        JSONObject jsonObject = JsonUtils.beanToJsonObject(map);
        log.info(jsonObject != null ? jsonObject.toJSONString() : null);
    }

    @Test
    void beanToJson3() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        JSONObject jsonObject = JsonUtils.beanToJsonObject(map, null);
        log.info(jsonObject != null ? jsonObject.toJSONString() : null);
    }

    @Test
    void stringToJsonByFastjson() {
        String result = JsonUtils.stringToJsonByFastjson("key", "value");
        log.info(result);
    }

    @Test
    void jsonToBean() {
        JSONObject json = new JSONObject();
        json.put("name", "vale");
        json.put("age", 10);
        Map result = JsonUtils.jsonToBean(json.toJSONString(), Map.class);
        log.info(result != null ? result.toString() : null);
    }

    @Test
    void jsonToBean1() {
        JSONObject json = new JSONObject();
        json.put("name", "vale");
        json.put("age", 10);
        Map result = JsonUtils.jsonToBean(json, Map.class);
        log.info(result != null ? result.toString() : null);
    }

    @Test
    void jsonToMap() {
        JSONObject json = new JSONObject();
        json.put("key", "vale");
        Map map = JsonUtils.jsonToMap(json.toJSONString());
        log.info(map != null ? map.toString() : null);
    }

    @Test
    void jsonToMap1() {
        JSONObject json = new JSONObject();
        json.put("key", "vale");
        Map map = JsonUtils.jsonToMap(json);
        log.info(map != null ? map.toString() : null);
    }

    @Test
    void jsonToBeanList() {
        JSONArray array = new JSONArray();
        JSONObject json = new JSONObject();
        json.put("name", "kathy");
        json.put("age", 10);
        array.add(json);
        List<Map> maps = JsonUtils.jsonToBeanList(array, Map.class);
        log.info(maps.get(0).toString());
    }

}