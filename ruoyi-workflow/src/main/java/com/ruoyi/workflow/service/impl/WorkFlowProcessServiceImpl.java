package com.ruoyi.workflow.service.impl;

import cn.hutool.core.lang.Assert;
import com.ruoyi.workflow.domain.Business;
import com.ruoyi.workflow.domain.ProcessRequest;
import com.ruoyi.workflow.domain.TaskRequest;
import com.ruoyi.workflow.service.BusinessService;
import com.ruoyi.workflow.service.IWorkFlowProcessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.rest.dto.history.HistoricTaskInstanceDto;
import org.camunda.bpm.engine.rest.dto.repository.DeploymentDto;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
@Service(value = "workFlowServiceImpl")
public class WorkFlowProcessServiceImpl implements IWorkFlowProcessService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private BusinessService businessService;

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
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ProcessInstanceDto startProcessInstanceByKey(ProcessRequest request) {
        ProcessInstance processInstance = null;
        Map<String, Object> variables = request.getVariables();
        variables.put("stater", request.getStater());
        //通过流程id或者流程key发起流程
        if (StringUtils.isNotBlank(request.getProcessDefId())) {
            processInstance = runtimeService.startProcessInstanceById(request.getProcessDefId(),
                    request.getBusinessKey(), variables);
        } else if (StringUtils.isNotBlank(request.getProcessDefKey())) {
            processInstance = runtimeService.startProcessInstanceByKey(request.getProcessDefKey(),
                    request.getBusinessKey(), variables);
        }
        Assert.isTrue(businessService.save(Business.builder()
                .businessKey(request.getBusinessKey())
                .processInstanceId(processInstance.getProcessInstanceId())
                .businessStater(request.getStater())
                .businessTitle(request.getTitle())
                .businessType(request.getBusinessType())
                .createTime(LocalDateTime.now())
                .build()),"业务 [%s] 存储发生异常",request.getBusinessKey());
        log.info(String.format("流程启动成功,流程实列Id [{%s}]", processInstance.getProcessInstanceId()));
        return ProcessInstanceDto.fromProcessInstance(processInstance);
    }

    @Override
    public List<HistoricTaskInstanceDto> getDoneTaskPage(String userId) {
        // 查询已办任务
        List<HistoricTaskInstance> taskInstances = historyService.createHistoricTaskInstanceQuery()
                .finished() // 已完成
                .taskAssignee(userId) // 分配给自己
                .orderByHistoricTaskInstanceEndTime()
                .desc().list();// 审批时间倒序
        return taskInstances.stream().map(HistoricTaskInstanceDto::fromHistoricTaskInstance).collect(Collectors.toList());
    }

    @Override
    public ProcessInstanceDto suspendProcess(String processInstanceId) {
        runtimeService.suspendProcessInstanceById(processInstanceId);
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        return ProcessInstanceDto.fromProcessInstance(processInstance);
    }

    @Override
    public ProcessInstanceDto activateProcess(String processInstanceId) {
        runtimeService.activateProcessInstanceById(processInstanceId);
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        return ProcessInstanceDto.fromProcessInstance(processInstance);
    }

    @Override
    public List<TaskDto> cancelProcess(TaskRequest request) {
        ActivityInstance tree = runtimeService.getActivityInstance(request.getProcessInstId());
        taskService.createComment(request.getTaskId(), request.getProcessInstId(), "撤回流程");
        if (tree == null) {
            throw new RuntimeException("活动实例不能为空");
        }
        runtimeService
                .createProcessInstanceModification(request.getProcessInstId())
                .cancelActivityInstance(getInstanceIdForActivity(tree, tree.getActivityId()))
                .startBeforeActivity(request.getActicityDefKey())
                .execute();
//        return processTaskService.queryActiveTask(QueryTaskRequest.builder().processInsId(request.getProcessInstId()).build());
        return null;
    }

    @Override
    public List<ProcessInstanceDto> queryProcessInstances(int start,int max) {
        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().active().listPage(start,max);
        return processInstances.stream().map(ProcessInstanceDto::fromProcessInstance).collect(Collectors.toList());
    }

    /**
     *
     * @param activityInstance activityInstance
     * @param activityId activityId
     */
    public String getInstanceIdForActivity(ActivityInstance activityInstance, String activityId) {
        ActivityInstance instance = getChildInstanceForActivity(activityInstance, activityId);
        if (instance != null) {
            return instance.getId();
        }
        return null;
    }

    public ActivityInstance getChildInstanceForActivity(ActivityInstance activityInstance, String activityId) {
        if (activityId.equals(activityInstance.getActivityId())) {
            return activityInstance;
        }
        for (ActivityInstance childInstance : activityInstance.getChildActivityInstances()) {
            ActivityInstance instance = getChildInstanceForActivity(childInstance, activityId);
            if (instance != null) {
                return instance;
            }
        }
        return null;
    }

}
