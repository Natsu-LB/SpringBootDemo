package com.example.demo.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class PageTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static Page page = new Page(1, 1, 2);

    @BeforeAll
    static void init() {
        List list = new ArrayList();
        list.add("a");
        list.add("b");
        page.setData(list);
    }

    @Test
    void getCurrentPage() {
        log.info(page.getCurrentPage() + "");
    }

    @Test
    void getTotalRecord() {
        log.info(page.getTotalRecord() + "");
    }

    @Test
    void getTotalPage() {
        log.info(page.getTotalPage() + "");
    }

    @Test
    void getStartRecord() {
        log.info(page.getStartRecord() + "");
    }


}