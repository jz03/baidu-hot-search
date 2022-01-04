package com.jz.baiduHotSearch.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jz.baiduHotSearch.pojo.HotSearchInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * 热搜公共类
 */
public class BaiduHotInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaiduHotInfo.class);

    /**
     * 获取百度热点信息
     * @return
     * @throws IOException
     */
    public static List<HotSearchInfo> getHotSearchInfo() throws IOException {
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
        LOGGER.info("当前热搜有"+hotSearchInfoList.size()+"条！");
        return hotSearchInfoList;
    }
}
