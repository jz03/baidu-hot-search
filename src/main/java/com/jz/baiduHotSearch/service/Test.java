package com.jz.baiduHotSearch.service;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("s-data","56453132");
        String s = JSON.toJSONString(map);
        System.out.println(s);
    }
}
