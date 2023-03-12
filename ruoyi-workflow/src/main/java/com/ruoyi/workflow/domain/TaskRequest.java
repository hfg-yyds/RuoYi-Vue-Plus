package com.ruoyi.workflow.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 任务参数对象
 *
 * @Author: Zero
 * @Date: 2022/5/25 11:46
 * @Description:
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TaskRequest-任务参数对象")
@Data
public class TaskRequest implements Serializable {

    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "流程实例ID")
    private String processInstId;

    @ApiModelProperty(value = "流程定义ID")
    private String processDefId;

    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @ApiModelProperty(value = "活动定义Key")
    private String acticityDefKey;

    @ApiModelProperty(value = "任务处理人")
    private String userId;

    @ApiModelProperty(value = "流程变量键值对")
    private Map<String, Object> variables;

    @ApiModelProperty(value = "任务变量键值对")
    private Map<String, Object> localVariables;

    @ApiModelProperty(value = "下一节点任务处理人")
    private String nextUserId;

    @ApiModelProperty(value = "驳回类型，1：起草节点，2：上一节点，3：目标节点")
    private String rejectType;

    @ApiModelProperty(value = "目标节点Id")
    private String toActId;

    @ApiModelProperty(value = "跳转类型，1：往前跳转，2：往回跳转")
    private String jumpType;

    @ApiModelProperty(value = "任务完成评论信息")
    private String message;

}
