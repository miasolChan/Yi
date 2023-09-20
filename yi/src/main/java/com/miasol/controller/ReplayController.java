package com.miasol.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miasol.common.dto.ReplayDto;
import com.miasol.common.lang.Result;
import com.miasol.entity.Replay;
import com.miasol.service.ReplayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;
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
@RequestMapping("/replay")
@Api(tags = "评论 相关接口")
public class ReplayController {

    @Autowired
    ReplayService replayService;

    /**
     * 发布评论
     * @param replayDto
     * @param comment
     */
    @ApiOperation(value = "发布评论",notes = "返回data:发布成功")
    @PostMapping("/release")
    public Result release(ReplayDto replayDto,String comment){
        Replay replay = new Replay();
        replay.setComment(comment);
        replay.setUserCode(replayDto.getUserCode());
        replay.setCommentTime(LocalDateTime.now());
        replay.setPostCode(replayDto.getPostCode());
        replayService.save(replay);
        return Result.succ("发布成功");

    }

    /**
     * 根据文章名获取文章评论
     * @param postCode
     */
    @ApiOperation(value = "根据文章名获取文章评论",notes = "返回data: List")
    @GetMapping("/getReplayList")
    public Result getReplayList(@ApiParam("文章唯一标识code")String postCode){
        QueryWrapper<Replay> wrapper = new QueryWrapper<>();
        wrapper.eq("post_code",postCode);
        List<Replay> list = replayService.list(wrapper);
        return Result.succ(list);
    }

}
