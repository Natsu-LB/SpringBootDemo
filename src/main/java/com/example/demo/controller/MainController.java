package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("MVCPathVariableInspection")
@Api(description = "主接口类")
@RestController
@RequestMapping(value = "/api/")
public class MainController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "GET方法测试",notes = "测试GET方法")
    @GetMapping("test")
    Result testGet() {
        log.info("test get ...");
        return Result.ok();
    }

    @ApiOperation(value = "GET方法测试",notes = "测试GET方法传参")
    @GetMapping("test/{id}")
    Result testGet(@PathVariable("id") Integer id) {
        log.info("test get ... " + id);
        return Result.ok();
    }

    @ApiOperation(value = "POST方法测试" ,notes = "测试POST方法")
    @PostMapping("test/{id}")
    Result testPost(@PathVariable(value = "id") Integer id) {
        log.info("test post ... " + id);
        return Result.ok();
    }

    @ApiOperation(value = "PUT方法测试" ,notes = "测试PUT方法")
    @PutMapping("test/{id}")
    Result testPut(@PathVariable(value = "id") Integer id) {
        log.info("test put ... " + id);
        return Result.ok();
    }

    @ApiOperation(value = "DELETE方法测试" ,notes = "测试DELETE方法")
    @DeleteMapping("test/{id}")
    Result testDelete(@PathVariable(value = "id") Integer id) {
        log.info("test delete ... " + id);
        return Result.ok();
    }

    @ApiOperation(value = "POST方法测试" ,notes = "测试POST方法")
    @PostMapping("test")
    Result testPost(@RequestBody JSONObject params) {
        log.info("test post ... " + params.toJSONString());
        return Result.ok();
    }

    @ApiOperation(value = "PUT方法测试" ,notes = "测试PUT方法")
    @PutMapping("test")
    Result testPut(@RequestBody JSONObject params) {
        log.info("test put ... " + params.toJSONString());
        return Result.ok();
    }

}
