package com.ruoyi.workflow.service;

import org.camunda.bpm.engine.rest.dto.history.HistoricTaskInstanceDto;
import org.camunda.bpm.engine.rest.dto.repository.DeploymentDto;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
     * 流程部署
     * @param multipartFile file
     * @param name 流程名称
     * @param source 流程来源
     * @return DeploymentDto
     * @throws IOException io
     */
    DeploymentDto deployProcess(MultipartFile multipartFile, String name, String source) throws IOException;

    /**
     * 查询已办任务
     *
     * @param userId 用户ID
     * @return 历史
     */
    List<HistoricTaskInstanceDto> getDoneTaskPage(String userId);

    /**
     * 查询正在运行的流程实例
     * @param start 流程实例开始索引
     * @param max 最大数量
     * @return list
     */
    List<ProcessInstanceDto> queryProcessInstances(int start,int max);

}
