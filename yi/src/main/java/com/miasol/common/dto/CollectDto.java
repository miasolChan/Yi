package com.miasol.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CollectDto {

    @ApiModelProperty("被收藏帖子")
    private String postCode;

    @ApiModelProperty("收藏用户")
    private String userCode;

    @ApiModelProperty("收藏状态（true收藏、false取消）")
    private Boolean collectStatus;

}
