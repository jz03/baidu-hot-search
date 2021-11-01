package com.jz.baiduHotSearch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 定时任务
 */
@Service
public class ScheduleTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    private HotSearchInfoService hotSearchInfoService;

    //每隔一分钟执行一次
    @Scheduled(cron="0 0/30 * * * ?")
    public void hotSearch() throws IOException {
        LOGGER.info("定时任务开始**********************");
        hotSearchInfoService.add();
        LOGGER.info("定时任务结束**********************");
    }
}
