package com.example.demo.controller;

import com.example.demo.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping(value = "/api/")
public class MainController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("test")
    Result testGet() {
        log.info("test get ...");
        return Result.ok();
    }

    @GetMapping("test/{id}")
    Result testGet(@PathVariable("id") Integer id) {
        log.info("test get ... " + id);
        return Result.ok();
    }

    @PostMapping("test/{id}")
    Result testPost(@PathVariable(value = "id") Integer id) {
        log.info("test post ... " + id);
        return Result.ok();
    }

}
