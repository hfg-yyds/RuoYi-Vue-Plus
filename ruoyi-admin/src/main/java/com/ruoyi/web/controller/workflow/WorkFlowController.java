package com.ruoyi.web.controller.workflow;

import com.ruoyi.common.web.controller.BaseController;
import com.ruoyi.common.web.domain.R;
import com.ruoyi.common.web.page.PageDomain;
import com.ruoyi.common.web.page.TableDataInfo;
import com.ruoyi.common.web.page.TableSupport;
import com.ruoyi.workflow.service.WorkFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.camunda.bpm.engine.rest.dto.repository.DeploymentDto;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
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
public class WorkFlowController extends BaseController {

    /**
     * 工作流服务
     */
    @Autowired
    private WorkFlowService workFlowService;

    /**
     * 流程部署
     * @param file file
     * @param name 流程名字
     * @param source 流程来源
     * @return DeploymentDto
     */
    @ApiOperation(value = "流程部署",notes = "流程部署")
    @PostMapping("/deployProcess")
    public R<DeploymentDto> deployProcess(@NotNull(message = "部署资源不能为空") MultipartFile file,
                                          String name, String source) {
        return R.run(()-> workFlowService.deployProcess(file, name, source));
    }

    /**
     * 查找正在运行的流程实例
     * @return R
     */
    @ApiOperation(value = "查找正在运行的流程实例")
    @GetMapping("/queryProcessInstances")
    public TableDataInfo queryProcessInstances() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        List<ProcessInstanceDto> list = workFlowService.queryProcessInstances(pageDomain.getPageNum(), pageDomain.getPageSize());
        return getDataTable(list);
    }


}
