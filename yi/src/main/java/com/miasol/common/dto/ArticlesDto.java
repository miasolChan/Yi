package com.miasol.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ArticlesDto {

    /**
     * 帖子标题
     */
    @ApiModelProperty("帖子标题")
    private String postTitle;

    /**
     * 帖子内容
     */
    @ApiModelProperty("帖子内容")
    private String postContent;

    /**
     * 发帖人
     */
    @ApiModelProperty("发帖人")
    private String userCode;

    /**
     * 装机类型（整机/DIY）
     */
    @ApiModelProperty("装机类型（整机/DIY）")
    private String configurateType;

    /**
     * 本帖装配价格
     */
    @ApiModelProperty("本帖装配价格")
    private String configuratePrice;

    /**
     * 所有硬件标识，以逗号分割
     */
    @ApiModelProperty("所有硬件标识，以逗号分割")
    private String hardwareCode;

}
