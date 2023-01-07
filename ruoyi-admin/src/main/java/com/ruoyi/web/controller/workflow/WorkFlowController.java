package com.ruoyi.web.controller.workflow;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.workflow.service.WorkFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

    /**
     * 工作流服务
     */
    @Autowired
    private WorkFlowService workFlowService;

    /**
     * 查找正在运行的流程实例
     * @param start
     * @param max
     * @return R
     */
    @ApiOperation(value = "查找正在运行的流程实例")
    @GetMapping("/queryProcessInstances/{start}/{max}")
    public R<?> queryProcessInstances(@PathVariable Integer start, @PathVariable Integer max) {
        return R.run(()->workFlowService.queryProcessInstances(start,max));
    }

}
