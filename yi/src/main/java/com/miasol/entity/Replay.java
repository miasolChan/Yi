package com.miasol.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
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
public class Replay implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 评论用户
     */
    private String userCode;

    /**
     * 评论时间
     */
    private LocalDateTime commentTime;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 被评论的帖子
     */
    private String postCode;


}
