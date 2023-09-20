package com.miasol.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class LikesDto {

    @ApiModelProperty("点赞帖子标识")
    private String postCode;


    @ApiModelProperty("点赞用户标识")
    private String userCode;

    /**
     *
     */
    @ApiModelProperty("点赞状态（true有效，false取消）")
    private Boolean likeStatus;


}
