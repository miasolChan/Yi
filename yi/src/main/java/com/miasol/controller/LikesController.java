package com.miasol.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miasol.common.dto.LikesDto;
import com.miasol.common.lang.Result;
import com.miasol.entity.Articles;
import com.miasol.entity.Likes;
import com.miasol.entity.User;
import com.miasol.service.LikesService;
import com.miasol.utils.MyTimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/likes")
@Api(tags = "点赞 相关接口")
public class LikesController {

    @Autowired
    LikesService likesService;

    /**
     * 添加点赞
     * @param likesDto
     */
    @ApiOperation(value = "添加点赞",notes = "返回data:收藏成功")
    @PostMapping("/add")
    public Result add(LikesDto likesDto){
        QueryWrapper<Likes>wrapper = new QueryWrapper<>();
        wrapper.eq("user_code", likesDto.getUserCode())
                .eq("post_code",likesDto.getPostCode());
        Likes likes = likesService.getOne(wrapper);

        if(likes==null){
            Likes nLikes = new Likes();
            likes.setPostCode(likesDto.getPostCode());
            likes.setUserCode(likesDto.getUserCode());
            likes.setLikeStatus(true);
            likes.setLikeTime(LocalDateTime.now());
        }else{
            likes.setLikeStatus(true);
            likesService.updateById(likes);

        }
        return Result.succ("点赞成功");
    }

    /**
     * 取消点赞
     * @param likesDto
     */
    @ApiOperation(value = "取消点赞",notes = "返回data:取消成功")
    @PostMapping("/cancel")
    public Result cancel (LikesDto likesDto){
        QueryWrapper<Likes>wrapper = new QueryWrapper<>();
        wrapper.eq("user_code", likesDto.getUserCode())
                .eq("post_code",likesDto.getPostCode());
        Likes likes = likesService.getOne(wrapper);
        likes.setLikeStatus(false);
        likesService.updateById(likes);
        return Result.succ("取消成功");
    }

}
