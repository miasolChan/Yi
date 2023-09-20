package com.miasol.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FollowsDto {

    @ApiModelProperty("自己标识")
    private String fromUsername;

    @ApiModelProperty("关注人标识")
    private String toUsername;

    @ApiModelProperty("关注状态,1关注，0取消")
    private Boolean focusStatus;

}
