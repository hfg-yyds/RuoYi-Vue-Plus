package com.ruoyi.web.controller.workflow;

import com.ruoyi.workflow.service.ProcessHistoryService;
import com.ruoyi.workflow.service.WorkFlowService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  工作流Api
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-01-07
 */
@Api(value = "工作流项目")
@RestController
@RequestMapping("/workflow")
public class WorkFlowController {

    @Autowired
    private WorkFlowService workFlowService;


}
