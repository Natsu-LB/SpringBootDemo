package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static java.util.Objects.requireNonNull;


class ResultTest {
    private Logger log = LoggerFactory.getLogger(ResultTest.class);

    @Test
    void build() {
        log.info("test build");
    }

    @Test
    void ok() {
        log.info(Result.ok(true).toString());
    }

    @Test
    void ok1() {
        log.info(Result.ok().toString());
    }

    @Test
    void build1() {
        log.info(Result.build(200,"success").toString());
    }

    @Test
    void getStatus() {
        Result success = Result.build(200, "success");
        log.info(success.getStatus().toString());
    }

    @Test
    void setStatus() {
        Result success = Result.build(200, "success");
        success.setStatus(400);
        log.info(success.getStatus().toString());
    }

    @Test
    void getMsg() {
        Result success = Result.build(200, "success");
        log.info(success.getMsg());
    }

    @Test
    void setMsg() {
        Result success = Result.build(200, "success");
        success.setMsg("failure");
        log.info(success.getMsg());
    }

    @Test
    void getData() {
        Result success = Result.build(200, "success",false);
        log.info(success.getData().toString());
    }

    @Test
    void setData() {
        Result result = new Result();
        result.setData(true);
        log.info(result.getData().toString());
    }

    @Test
    void formatToPojo() {
        JSONObject json = new JSONObject();
        json.put("status",200);
        json.put("msg","success");
        json.put("data",null);
        String string = json.toJSONString();
        log.info(requireNonNull(Result.formatToPojo(string, null)).toString());

    }

    @Test
    void format() {
        JSONObject json = new JSONObject();
        json.put("status",200);
        json.put("msg","success");
        String string = json.toJSONString();
        log.info(requireNonNull(Result.format(string)).toString());
    }

    @Test
    void formatToList() {
        String[] strings = {"1","2"};
        JSONObject json = new JSONObject();
        json.put("status",200);
        json.put("msg","success");
        json.put("data",strings);
        log.info(requireNonNull(Result.formatToList(json.toJSONString(), String.class)).toString());

    }
}