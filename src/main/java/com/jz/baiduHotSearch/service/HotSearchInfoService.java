package com.jz.baiduHotSearch.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jz.baiduHotSearch.mapper.HotSearchInfoMapper;
import com.jz.baiduHotSearch.pojo.HotSearchInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class HotSearchInfoService {

    @Autowired
    private HotSearchInfoMapper hotSearchInfoMapper;

    public void add() throws IOException {
        List<HotSearchInfo> hotSearchInfo = this.getHotSearchInfo();
        long branchId = System.currentTimeMillis();
        Date date = new Date();
        int count = hotSearchInfoMapper.insert(hotSearchInfo, branchId, date);
        System.out.println(count);
    }

    /**
     * 获取百度热点信息
     * @return
     * @throws IOException
     */
    public List<HotSearchInfo> getHotSearchInfo() throws IOException {
        //1.获取热搜页面
        String url = "https://top.baidu.com/board?tab=realtime";
        Document document = Jsoup.connect(url).get();
        //2.获取返回结果信息
        Elements select = document.select("#sanRoot");
        String comment = select.comments().get(0).toString();
        //3.结果信息转换为复合json的格式
        String res = comment.replaceAll("<!--", "")
                .replaceAll("-->", "")
                .replaceAll("s-data:","");
        //4.解析json字符串
        JSONObject jsonObject = JSON.parseObject(res);
        JSONArray cards = jsonObject.getJSONObject("data").getJSONArray("cards");
        JSONObject cards01 = cards.getJSONObject(0);
        JSONArray content = cards01.getJSONArray("content");
        //5.将json对象转换为相应的对象
        List<HotSearchInfo> hotSearchInfoList = JSONObject.parseArray(content.toJSONString(), HotSearchInfo.class);
        System.out.println("当前热搜有"+hotSearchInfoList.size()+"条！");
        return hotSearchInfoList;
    }
}
