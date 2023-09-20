package com.miasol.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2021-05-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HComputer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 笔记本、整机 品牌
     */
    private String brand;

    /**
     * 笔记本/整机
     */
    private String type;

    /**
     * 图片地址
     */
    private String picture;

    /**
     * 名字
     */
    private String name;


    /**
     * 价格
     */
    private String price;


}
