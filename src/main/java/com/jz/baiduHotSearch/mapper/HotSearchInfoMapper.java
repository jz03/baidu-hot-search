package com.jz.baiduHotSearch.mapper;

import com.jz.baiduHotSearch.pojo.HotSearchInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface HotSearchInfoMapper {

    int insert(@Param("hotSearchInfoList") List<HotSearchInfo> hotSearchInfoList,
               @Param("branchId") long branchId,
               @Param("date") Date date);
}
