package com.jz.baiduHotSearch;

import com.jz.baiduHotSearch.service.HotSearchInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class BaiduHotSearchApplicationTests {

    @Autowired
    private HotSearchInfoService hotSearchInfoService;

    /**
     * 热搜入库测试
     *
     * @throws IOException
     */
    @Test
    void contextLoads() throws IOException {
        hotSearchInfoService.add();
    }

}
