package com.jz.baiduHotSearch.controller;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class HotSearchControllerTest {

    @Test
    void test1() {
        double dou = 1636274094956D;
        String s =  new BigDecimal(String.valueOf(dou)).toString();
        System.out.println(s);
    }
}