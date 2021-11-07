package com.jz.baiduHotSearch.controller;

import com.jz.baiduHotSearch.pojo.HotInfo;
import com.jz.baiduHotSearch.service.HotSearchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("hotSearch")
public class HotSearchController {
    @Autowired
    private HotSearchInfoService hotSearchInfoService;

    @RequestMapping("/test")
    public String test(){
        System.out.println("请求数据成功");
        return "success";
    }

    /**
     * 抓取热搜数据
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("/grabHotSearchData")
    public String grabHotSearchData() throws IOException {
        hotSearchInfoService.recordHotSearchInfo();
        return "success";
    }

    /**
     * 查询热搜消息
     * @return
     */
    @RequestMapping("/findHotInfoList")
    public List<HotInfo> findHotInfoList(@RequestParam("query") String query){
        return hotSearchInfoService.findHotInfoList(query);
    }

    /**
     * 查询信息历史
     * @param query
     * @param id
     * @return
     */
    @RequestMapping("/findHotInfoHistoryList")
    public List<Map<String,Object>> findHotInfoHistoryList(@RequestParam("query") String query,
                                                           @RequestParam("id") String id){
        return hotSearchInfoService.findHotInfoHistoryList(query,id);
    }
}
