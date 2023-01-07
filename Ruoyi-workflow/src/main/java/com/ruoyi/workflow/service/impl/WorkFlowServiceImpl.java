package com.ruoyi.workflow.service.impl;

import com.ruoyi.workflow.service.WorkFlowService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.rest.dto.history.HistoricTaskInstanceDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  Camunda流程引擎服务
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-01-07
 */
@Slf4j
@Service
public class WorkFlowServiceImpl implements WorkFlowService {

    @Override
    public List<HistoricTaskInstanceDto> getDoneTaskPage(String userId) {
        return null;
    }

}
