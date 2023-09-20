package com.miasol.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miasol.common.dto.ArticlesDto;
import com.miasol.common.lang.Result;
import com.miasol.entity.Articles;
import com.miasol.service.ArticlesService;
import com.miasol.utils.MyTimeUtils;
import com.miasol.utils.UuidUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miasol
 *
 */
@RestController
@RequestMapping("/articles")
@Api(tags = "帖子 相关接口")
public class ArticlesController {

    @Autowired
    ArticlesService articlesService;

    /**
     *获取所有文章的列表
     */
    @ApiOperation(value="获取所有文章的列表",notes = "返回data : List")
    @GetMapping("/getAll")
    public Result getAll(){
        List<Articles>list = articlesService.list();
        return Result.succ(list);
    }

    /**
     *通过发帖人Code获取文章列表
     *
     */
    @ApiOperation(value = "通过发帖人Code获取文章列表",notes="返回data:List")
    @GetMapping("/getAllByUserCode")
    public Result getAllByUserName(@ApiParam("用户的唯一标识符Code") String code){
        QueryWrapper<Articles> wrapper = new QueryWrapper<>();
        wrapper.eq("user_code",code);
        List<Articles>list = articlesService.list(wrapper);
        return Result.succ(list);
    }

    /**
     * 根据类型选择文章列表
     * @param type
     */
    @ApiOperation(value = "根据类型选择文章列表",notes = "返回data ：List")
    @GetMapping("/getListByType")
    public Result getListByType(@ApiParam("传入类型（整机/DIY）") String type){
        QueryWrapper<Articles>wrapper = new QueryWrapper<>();
        wrapper.eq("configurate_type",type);
        List<Articles>list = articlesService.list(wrapper);
        return Result.succ(list);
    }


    /**
     * 获取发帖人唯一标识
     * @param postCode
     */
    @ApiOperation(value = "通过文章code获取发帖人唯一标识",notes = "返回data :<K,V> ")
    @GetMapping("/getUserCode")
    public Result getUserCode(@ApiParam("文章唯一标识code") String postCode){
        QueryWrapper<Articles>wrapper = new QueryWrapper<>();
        wrapper.eq("post_code",postCode);
        Articles articles = articlesService.getOne(wrapper);
        return Result.succ(MapUtil.builder().put("UserCode",articles.getUserCode()));
    }


    /**
     *通过帖子标题获取postCode（唯一标识）
     * @param postTitle
     */
    @ApiOperation(value = "通过 帖子标题 获取postCode（文章唯一标识",notes = "返回data: <k,v>")
    @GetMapping("/getPostCode")
    public Result getPostCode(@ApiParam("文章标题")String postTitle){
        QueryWrapper<Articles>wrapper = new QueryWrapper<>();
        wrapper.eq("post_title",postTitle);
        Articles articles = articlesService.getOne(wrapper);
        return Result.succ(MapUtil.builder().put("PostCode",articles.getPostCode()));
    }

    /**
     * 通过标识获取帖子名
     * @param postCode
     */
    @ApiOperation(value = "通过code标识获取帖子标题",notes="返回data:<K,V>")
    @GetMapping("/getPostTitle")
    public Result getPostTitle(@ApiParam("文章唯一标识code")String postCode){
        QueryWrapper<Articles>wrapper = new QueryWrapper<>();
        wrapper.eq("post_code",postCode);
        Articles articles = articlesService.getOne(wrapper);
        return Result.succ(MapUtil.builder().put("PostTitle",articles.getPostTitle()));
    }
    /**
     * 获取指定用户的点赞帖子列表
     * @param userCode
     * @return
     */
    @ApiOperation(value = "获取指定用户的点赞帖子列表",notes = "返回data: List")
    @GetMapping("/getLikeList")
    public Result getLikeList (@ApiParam("用户唯一标识code")String userCode){
        return Result.succ(articlesService.getLikeList(userCode));
    }

    /**
     * 获取指定用户的收藏帖子列表
     * @param userCode
     * @return postCode userCode likeTime
     */
    @ApiOperation(value = "获取指定用户的收藏帖子列表",notes = "返回data: List")
    @GetMapping("/getCollectList")
    public Result getCollectList (@ApiParam("用户唯一标识code")String userCode){
        return Result.succ(articlesService.getCollectList(userCode));
    }


    /**
     * 根据价格区间查询
     */
    @ApiOperation(value = "根据价格区间查询",notes = "返回data: List")
    @GetMapping("sortBtwPrice")
    public Result sortBtwPrice(@ApiParam("最低价格")String min,@ApiParam("最高价格") String max){
        QueryWrapper<Articles>wrapper = new QueryWrapper<>();
        wrapper.between("configurate_price",min,max);
        List<Articles> list = articlesService.list(wrapper);
        return Result.succ(list);
    }

    /**
     * 价格降序
     * orderByDesc
     */
    @ApiOperation(value = "价格降序",notes = "返回data: List")
    @GetMapping("/priceOrderByDesc")
    public Result priceOrderByDesc(){
        QueryWrapper<Articles>wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("configurate_price");
        List<Articles> list = articlesService.list();
        return Result.succ(list);
    }
    /**
     * 价格升序
     */
    @ApiOperation(value = "价格升序",notes = "返回data: List")
    @GetMapping("/priceOrderByAsc")
    public Result priceOrderByAsc(){
        QueryWrapper<Articles>wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("configurate_price");
        List<Articles> list = articlesService.list();
        return Result.succ(list);
    }

    /**
     * 发布文章
     *
     */
    @ApiOperation(value = "发布文章",notes = "返回data:发布成功")
    @PostMapping("/release")
    public Result release(ArticlesDto articlesDto){
        Articles articles = new Articles();
        articles.setPostTitle(articlesDto.getPostTitle());
        articles.setPostContent(articlesDto.getPostContent());
        articles.setPostTime(MyTimeUtils.getLocalDate());
        articles.setUserCode(articlesDto.getUserCode());
        articles.setPostCode(UuidUtils.getUUID());
        articles.setConfiguratePrice(articlesDto.getConfiguratePrice());
        articles.setConfigurateType(articlesDto.getConfigurateType());
        articlesService.save(articles);
        return Result.succ("发布成功");
    }

}
