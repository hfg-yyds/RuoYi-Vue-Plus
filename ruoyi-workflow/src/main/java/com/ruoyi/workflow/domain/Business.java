package com.ruoyi.workflow.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *  业务公共表表
 * </p>
 *
 * @author Zero
 * @since 2022-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("business")
@Builder
@ApiModel(value="Business对象", description="")
public class Business implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "business_key", type = IdType.ASSIGN_UUID)
    private String businessKey;

    @ApiModelProperty(value = "流程实例Id")
    @TableField("process_instance_id")
    private String processInstanceId;

    @ApiModelProperty(value = "业务类型")
    @TableField("business_type")
    private String businessType;

    @ApiModelProperty(value = "业务标题")
    @TableField("business_title")
    private String businessTitle;

    @ApiModelProperty(value = "业务申请人")
    @TableField("business_stater")
    private String businessStater;

    @ApiModelProperty(value = "申请日期")
    @TableField("create_time")
    private LocalDateTime createTime;


    public static final String BUSINESS_KEY = "business_key";

    public static final String PROCESS_INSTANCE_ID = "process_instance_id";

    public static final String BUSINESS_TYPE = "business_type";

    public static final String BUSINESS_TITLE = "business_title";

    public static final String BUSINESS_STATER = "business_stater";

    public static final String CREATE_TIME = "create_time";

}
