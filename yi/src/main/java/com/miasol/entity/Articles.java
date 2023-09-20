package com.miasol.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author miasol
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("文章实体类")
public class Articles implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("帖子标题")
    private String postTitle;


    @ApiModelProperty("帖子内容")
    private String postContent;

    @ApiModelProperty("发帖时间")
    private LocalDate postTime;

    @ApiModelProperty("id标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("发帖人")
    private String userCode;


    @ApiModelProperty("随机数生成帖子唯一标识")
    private String postCode;


    @ApiModelProperty("装机类型（整机/DIY）")
    private String configurateType;


    @ApiModelProperty("本帖装配价格")
    private String configuratePrice;


    @ApiModelProperty("所有硬件，以逗号分割")
    private String hardwareCode;


}
