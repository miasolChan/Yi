package com.miasol.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReplayDto {


    @ApiModelProperty("评论用户")
    private String userCode;


    @ApiModelProperty("被评论的帖子")
    private String postCode;
}
