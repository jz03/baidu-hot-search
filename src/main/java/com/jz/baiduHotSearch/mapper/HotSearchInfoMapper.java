package com.jz.baiduHotSearch.mapper;

import com.jz.baiduHotSearch.pojo.HotBranch;
import com.jz.baiduHotSearch.pojo.HotInfo;
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

    int insertHotBranch(@Param("hotBranchList") List<HotBranch> hotBranchList,
                        @Param("branchId") long branchId,
                        @Param("date") Date date);

    int insertHotInfo(@Param("hotInfoList") List<HotInfo> hotInfoList,@Param("date") Date date);

    HotInfo findHotInfo(@Param("query") String query);

    int upateHotInfo(@Param("query") String query,@Param("desc") String desc);
}
