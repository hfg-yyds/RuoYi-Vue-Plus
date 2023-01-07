package com.ruoyi.workflow.service;

import org.camunda.bpm.engine.rest.dto.history.HistoricTaskInstanceDto;

import java.util.List;

/**
 * <p>
 *  流程引擎服务
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-01-07
 */
public interface WorkFlowService {

    /**
     * 查询已办任务
     *
     * @param userId 用户ID
     * @return 历史
     */
    List<HistoricTaskInstanceDto> getDoneTaskPage(String userId);

}
