package com.miasol.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2021-05-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Likes implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞帖子标题
     */
    private String postCode;

    /**
     * 点赞用户标识
     */
    private String userCode;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 点赞状态（取消或有效）
     */
    private Boolean likeStatus;

    /**
     * 点赞时间
     */
    private LocalDateTime likeTime;


}
