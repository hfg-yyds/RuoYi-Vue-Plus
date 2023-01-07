package com.ruoyi.workflow.service.impl;

import com.ruoyi.workflow.service.WorkFlowService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.rest.dto.history.HistoricTaskInstanceDto;
import org.camunda.bpm.engine.rest.dto.repository.DeploymentDto;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public DeploymentDto deployProcess(MultipartFile multipartFile, String name, String source) throws IOException {
        Deployment deploy = repositoryService.createDeployment()
                .name(name)
                .source(source)
                .addInputStream(multipartFile.getOriginalFilename(),multipartFile.getInputStream())
                .deploy();
        return DeploymentDto.fromDeployment(deploy);
    }

    @Override
    public List<HistoricTaskInstanceDto> getDoneTaskPage(String userId) {
        return null;
    }

    @Override
    public List<ProcessInstanceDto> queryProcessInstances(int start,int max) {
        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().active().listPage(start,max);
        return processInstances.stream().map(ProcessInstanceDto::fromProcessInstance).collect(Collectors.toList());
    }

}
