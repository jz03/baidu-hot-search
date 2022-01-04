package com.jz.baiduHotSearch.service;

import com.alibaba.druid.util.StringUtils;
import com.jz.baiduHotSearch.common.BaiduHotInfo;
import com.jz.baiduHotSearch.mapper.HotSearchInfoMapper;
import com.jz.baiduHotSearch.pojo.HotBranch;
import com.jz.baiduHotSearch.pojo.HotInfo;
import com.jz.baiduHotSearch.pojo.HotSearchInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<HotSearchInfo> hotSearchInfoList = BaiduHotInfo.getHotSearchInfo();
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

    /***
     * 查询热搜消息
     *
     * @param query
     * @return
     */
    public List<HotInfo> findHotInfoList(String query){
        LOGGER.info("查询热搜信息-----------------");
        query = query.trim();
        List<HotInfo> hotInfoList = hotSearchInfoMapper.findHotInfoList(query);
        return hotInfoList;
    }

    /***
     * 查询信息历史
     *
     * @param query
     * @param id
     * @return
     */
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
     * 查询日期对应的数目
     * @return
     */
    public Map<String, Object> findHotCountDate(){
        List<HashMap<String, Object>> mapperResList = hotSearchInfoMapper.findHotCountDate();
        List<Object> dateList = mapperResList.stream().map(item -> item.get("cdate")).collect(Collectors.toList());
        List<Object> numList = mapperResList.stream().map(item -> item.get("num")).collect(Collectors.toList());
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("xData",dateList);
        resMap.put("yData",numList);
        return resMap;
    }

    public List<HotInfo> findHotInfoListForDate(String date){
        return hotSearchInfoMapper.findHotInfoListForDate(date);
    }
}
