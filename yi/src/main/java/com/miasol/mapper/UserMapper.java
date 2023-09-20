package com.miasol.mapper;

import com.miasol.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author miasol
 *
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
