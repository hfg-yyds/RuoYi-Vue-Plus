package com.ruoyi.workflow.service;

import com.ruoyi.workflow.domain.ProcessRequest;
import com.ruoyi.workflow.domain.TaskRequest;
import org.camunda.bpm.engine.rest.dto.history.HistoricTaskInstanceDto;
import org.camunda.bpm.engine.rest.dto.repository.DeploymentDto;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
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
public interface IWorkFlowProcessService {

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
     * 根据流程定义key、流程定义id启动流程
     *
     * @param request
     * @return
     */
    ProcessInstanceDto startProcessInstanceByKey(ProcessRequest request);

    /**
     * 查询已办任务
     *
     * @param userId 用户ID
     * @return 历史
     */
    List<HistoricTaskInstanceDto> getDoneTaskPage(String userId);

    /**
     * 流程挂起
     * @param processInstanceId
     */
    ProcessInstanceDto suspendProcess(String processInstanceId);

    /**
     * 流程激活
     * @param processInstanceId
     */
    ProcessInstanceDto activateProcess(String processInstanceId);

    /**
     * 流程撤回
     *
     * @param request
     */
    List<TaskDto> cancelProcess(TaskRequest request);

    /**
     * 查询正在运行的流程实例
     * @param start 流程实例开始索引
     * @param max 最大数量
     * @return list
     */
    List<ProcessInstanceDto> queryProcessInstances(int start,int max);

}
