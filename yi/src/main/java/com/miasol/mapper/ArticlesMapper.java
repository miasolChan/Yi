package com.miasol.mapper;

import com.miasol.entity.Articles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
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
public interface ArticlesMapper extends BaseMapper<Articles> {

    @Select("SELECT articles.* FROM likes,articles WHERE likes.user_code = #{userCode} AND likes.post_code = articles.post_code")
    List<Articles> getLikeList(@Param("userCode") String userCode);

    @Select("SELECT articles.* FROM collect,articles WHERE collect.user_code = #{userCode} AND collect.post_code = articles.post_code")
    List<Articles> getCollectList(@Param("userCode") String userCode);
    
    
}



