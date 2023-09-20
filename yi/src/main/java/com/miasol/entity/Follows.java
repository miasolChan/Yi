package com.miasol.entity;

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
public class Follows implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自己id
     */
    private String fromUsername;

    /**
     * 关注人id
     */
    private String toUsername;

    /**
     * 关注状态
     */
    private Boolean focusStatus;

    private Integer id;


}
