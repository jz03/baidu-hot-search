package com.jz.baiduHotSearch.service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jz.baiduHotSearch.mapper.HotSearchInfoMapper;
import com.jz.baiduHotSearch.pojo.HotBranch;
import com.jz.baiduHotSearch.pojo.HotInfo;
import com.jz.baiduHotSearch.pojo.HotSearchInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * 热搜service
 */
@Service
public class HotSearchInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HotSearchInfoService.class);

    @Autowired
    private HotSearchInfoMapper hotSearchInfoMapper;

    /**
     * 记录热搜信息
     */
    public void recordHotSearchInfo() throws IOException {
        LOGGER.info("记录热搜信息-----------------");
        List<HotSearchInfo> hotSearchInfoList = this.getHotSearchInfo();
        //更新热点信息
        List<HotInfo> hotInfoList = hotSearchInfoList.stream().map(item -> {
            HotInfo hotInfoSearch = hotSearchInfoMapper.findHotInfo(item.getQuery());
            //1.若不存在，添加
            if(hotInfoSearch == null){
                HotInfo hotInfo = new HotInfo();
                hotInfo.setQuery(item.getQuery());
                hotInfo.setImg(item.getImg());
                hotInfo.setWord(item.getWord());
                hotInfo.setDesc(item.getDesc());
                hotInfo.setUrl(item.getUrl());
                return hotInfo;
            }else{
                //2.若存在，描述不存在，更新描述
                if(!item.getDesc().equals("")&&(hotInfoSearch.getDesc()==null||hotInfoSearch.getDesc().equals(""))){
                    int count = hotSearchInfoMapper.upateHotInfo(item.getQuery(), item.getDesc());
                    LOGGER.info("更新热点信息详情" + count);
                }
            }
            return null;
        }).collect(Collectors.toList());
        //过滤掉空值
        List<HotInfo> hotInfoListFilter = hotInfoList.stream().filter(item -> {
            if (item == null) {
                return false;
            } else {
                return true;
            }
        }).collect(Collectors.toList());
        //入库
        if(hotInfoListFilter.size()>0){
            int hotInfoCount = hotSearchInfoMapper.insertHotInfo(hotInfoListFilter, new Date());
            LOGGER.info("热搜信息更新:" + hotInfoCount);
        }
        //保存批次信息
        List<HotBranch> hotBranchList = hotSearchInfoList.stream().map(item -> {
            HotBranch hotBranch = new HotBranch();
            hotBranch.setHotInfoId(hotSearchInfoMapper.findHotInfo(item.getQuery()).getId());
            hotBranch.setHotScore(item.getHotScore());
            hotBranch.setIndex(item.getIndex());
            return hotBranch;
        }).collect(Collectors.toList());
        if(hotBranchList.size()>0){
            int branchCount = hotSearchInfoMapper.insertHotBranch(hotBranchList,System.currentTimeMillis(),new Date());
            LOGGER.info("热搜批次更新:" + branchCount);
        }
    }

    public List<HotInfo> findHotInfoList(String query){
        LOGGER.info("查询热搜信息-----------------");
        query = query.trim();
        List<HotInfo> hotInfoList = hotSearchInfoMapper.findHotInfoList(query);
        return hotInfoList;
    }

    public Map<String, Object> findHotInfoHistoryList(String query,String id){
        LOGGER.info("查询热搜信息历史-----------------");
        query = query.trim();
        if (StringUtils.isEmpty(query) && StringUtils.isEmpty(id)) {
            return null;
        }
        List<Map<String, Object>> hotInfoList = hotSearchInfoMapper.findHotInfoHistoryList(query,id);
        //X时间轴的数据
        List<Object> createDateList = hotInfoList.stream().map(item -> item.get("createDate")).collect(Collectors.toList());
        //Y热搜指数的数据
        List<Object> hotScoreList = hotInfoList.stream().map(item -> item.get("hotScore")).collect(Collectors.toList());
        List<Object> indexList = hotInfoList.stream().map(item -> item.get("index")).collect(Collectors.toList());
        Map resMap = new HashMap();
        resMap.put("data",hotInfoList);
        resMap.put("xData",createDateList);
        resMap.put("yData",hotScoreList);
        resMap.put("yIndexData",indexList);
        return resMap;
    }

    /**
     * 获取百度热点信息
     * @return
     * @throws IOException
     */
    private List<HotSearchInfo> getHotSearchInfo() throws IOException {
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
