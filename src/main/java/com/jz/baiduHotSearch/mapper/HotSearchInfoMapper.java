package com.jz.baiduHotSearch.mapper;

import com.jz.baiduHotSearch.pojo.HotBranch;
import com.jz.baiduHotSearch.pojo.HotInfo;
import com.jz.baiduHotSearch.pojo.HotSearchInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
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
}
