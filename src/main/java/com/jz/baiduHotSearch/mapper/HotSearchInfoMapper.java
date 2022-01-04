package com.jz.baiduHotSearch.mapper;

import com.jz.baiduHotSearch.pojo.HotBranch;
import com.jz.baiduHotSearch.pojo.HotInfo;
import com.jz.baiduHotSearch.pojo.HotSearchInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface HotSearchInfoMapper {

    int insert(@Param("hotSearchInfoList") List<HotSearchInfo> hotSearchInfoList,
               @Param("branchId") long branchId,
               @Param("date") Date date);

    int insertHotBranch(@Param("hotBranchList") List<HotBranch> hotBranchList,
                        @Param("branchId") long branchId,
                        @Param("date") Date date);

    int insertHotInfo(@Param("hotInfoList") List<HotInfo> hotInfoList,@Param("date") Date date);

    /**准确查询热搜信息*/
    HotInfo findHotInfo(@Param("query") String query);

    /**准确更新热搜信息*/
    int upateHotInfo(@Param("query") String query,@Param("desc") String desc);

    /**模糊查询热搜信息*/
    List<HotInfo> findHotInfoList(@Param("query") String query);

    /**查询历史信息*/
    List<Map<String,Object>> findHotInfoHistoryList(@Param("query") String query,@Param("id") String id);

    /**查询所有日期对应的消息数目*/
    List<HashMap<String,Object>> findHotCountDate();

    /**根据某一天的日期查询出当前的详细热搜信息*/
    List<HotInfo> findHotInfoListForDate(String date);
}
