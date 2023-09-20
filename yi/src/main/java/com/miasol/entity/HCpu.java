package com.miasol.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HCpu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    /**
     * 图片
     */
    private String picture;

    /**
     * 参数
     */
    private String parameter;

    /**
     * （必需、非必需）
     */
    private Boolean type;

    /**
     * 价格
     */
    private String price;

    /**
     * 热度
     */
    private String hotDegree;


}
