package com.ruoyi.workflow.service;

import org.camunda.bpm.engine.rest.dto.history.HistoricTaskInstanceDto;

import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/5/24 18:50
 * @Description:
 */
public interface ProcessHistoryService {
    /**
     * 查询已办任务
     *
     * @param userId
     * @return
     */
    List<HistoricTaskInstanceDto> getDoneTaskPage(String userId);
}
