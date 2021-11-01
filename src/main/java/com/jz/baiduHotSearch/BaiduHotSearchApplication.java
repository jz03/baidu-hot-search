package com.jz.baiduHotSearch;

import com.jz.baiduHotSearch.service.HotSearchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaiduHotSearchApplication implements CommandLineRunner {

    @Autowired
    private HotSearchInfoService hotSearchInfoService;

    public static void main(String[] args) {
        SpringApplication.run(BaiduHotSearchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        hotSearchInfoService.add();
    }
}
