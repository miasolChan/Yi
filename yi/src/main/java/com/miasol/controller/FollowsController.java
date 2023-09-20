package com.miasol.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miasol.common.dto.FollowsDto;
import com.miasol.common.dto.LikesDto;
import com.miasol.common.lang.Result;
import com.miasol.entity.Follows;
import com.miasol.entity.Follows;
import com.miasol.entity.Likes;
import com.miasol.service.FollowsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;
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
@RequestMapping("/follows")
@Api(tags = "关注 相关接口")
public class FollowsController {

    @Autowired
    FollowsService followsService;

    /**
     * 获取当前用户的粉丝数
     * @param userCode (to)
     */
    @ApiOperation(value = "获取当前用户的粉丝数",notes = "返回data:<k,v>")
    @GetMapping("/getFansCount")
    public Result getFansCount(@ApiParam("用户唯一标识code") String userCode){
        QueryWrapper<Follows>wrapper = new QueryWrapper<>();
        wrapper.eq("to_username",userCode).eq("focus_status",true);
        int count = followsService.count(wrapper);
        return Result.succ(MapUtil.builder().put("count",count));
    }


    /**
     * 获取当前用户的粉丝列表
     * @param  
     */
    @ApiOperation(value = "获取当前用户的粉丝列表",notes = "返回data:list")
    @GetMapping("/getFansList")
    public Result getFansList(@ApiParam("用户唯一标识code") String userCode){
        QueryWrapper<Follows>wrapper = new QueryWrapper<>();
        wrapper.select("to_username").eq("to_username",userCode).eq("focus_status",true);
        List<Follows>list = followsService.list(wrapper);
        return Result.succ(list);
    }

    /**
     * 获取当前用户关注的人数
     * @param userCode （from）
     */
    @ApiOperation(value = "获取当前用户关注的人数",notes = "返回data:<k,v>)")
    @GetMapping("/getFollowsCount")
    public Result getFollowsCount(@ApiParam("用户唯一标识code") String userCode){
        QueryWrapper<Follows>wrapper = new QueryWrapper<>();
        wrapper.eq("from_username",userCode).eq("focus_status",true);
        int count = followsService.count(wrapper);
        return Result.succ(MapUtil.builder().put("count",count));
    }
    
    /**
     * 获取当前用户关注人列表
     * @param userCode
     */
    @ApiOperation(value = "获取当前用户关注人列表",notes = "返回data:list")
    @GetMapping("/getFollowsList")
    public Result getFollowsList(@ApiParam("用户唯一标识code") String userCode){
        QueryWrapper<Follows>wrapper = new QueryWrapper<>();
        wrapper.select("from_username").eq("from_username",userCode).eq("focus_status",true);
        List<Follows>list = followsService.list(wrapper);
        return Result.succ(list);
    }

    /**
     * 点击关注
     * @param followsDto
     */
    @ApiOperation(value = "点击关注",notes = "返回data:关注成功")
    @PostMapping("/add")
    public Result add(FollowsDto followsDto){
        QueryWrapper<Follows>wrapper = new QueryWrapper<>();
        wrapper.eq("from_username", followsDto.getFromUsername())
                .eq("to_username",followsDto.getToUsername());
        Follows follows = followsService.getOne(wrapper);

        if(follows==null){
            Follows nFollows = new Follows();
            follows.setFromUsername(followsDto.getFromUsername());
            follows.setToUsername(followsDto.getToUsername());
            follows.setFocusStatus(true);
        }else{
            follows.setFocusStatus(true);
            followsService.updateById(follows);

        }
        return Result.succ("点赞成功");
    }
    /**
     * 取消关注
     * @param followsDto
     */
    @PostMapping("/cancel")
    public Result cancel(FollowsDto followsDto){
        QueryWrapper<Follows>wrapper = new QueryWrapper<>();
        wrapper.eq("from_username", followsDto.getFromUsername())
                .eq("to_username",followsDto.getToUsername());
        Follows follows = followsService.getOne(wrapper);
        follows.setFocusStatus(false);
        followsService.updateById(follows);
        return Result.succ("取消成功");
    }
}
