package com.jz.baiduHotSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BaiduHotSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaiduHotSearchApplication.class, args);
    }
}
