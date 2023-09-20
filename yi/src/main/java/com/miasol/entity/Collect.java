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
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Collect implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 被收藏帖子
     */
    private String postCode;

    /**
     * 收藏用户
     */
    private String userCode;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收藏状态（收藏、取消）
     */
    private Boolean collectStatus;

    /**
     * 收藏时间
     */
    private LocalDateTime collectTime;


}
