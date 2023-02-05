package com.ruoyi.workflow.domain;

import lombok.Data;

/**
 * <p>
 *  流程实例返回DTO
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-01-07
 */
@Data
public class ProcessInstanceResponseDto {
    /**
     * 流程Id
     */
    private String id;
    /**
     * 流程业务Key
     */
    private String businessKey;
    /**
     * 流程
     */
    private String end;
    /**
     * 流程状态
     */
    private String suspended;
    /**
     * 创建人
     */
    private String createdBy;
}
