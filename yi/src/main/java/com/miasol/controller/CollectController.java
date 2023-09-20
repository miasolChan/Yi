package com.miasol.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miasol.common.dto.CollectDto;
import com.miasol.common.lang.Result;
import com.miasol.entity.Collect;
import com.miasol.service.CollectService;
import com.miasol.utils.MyTimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
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
@RequestMapping("/collect")
@Api(tags = "收藏 相关接口")
public class CollectController {

    @Autowired
    CollectService collectService;

    /**
     * 添加收藏
     * @param collectDto
     */
    @ApiOperation(value = "添加收藏",notes = "返回data:收藏成功")
    @PostMapping("/add")
    public Result add(CollectDto collectDto){
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_code", collectDto.getUserCode())
                .eq("post_code",collectDto.getPostCode());
        Collect collect = collectService.getOne(wrapper);

        if(collect==null){
            Collect nCollect = new Collect();
            nCollect.setPostCode(collectDto.getPostCode());
            nCollect.setUserCode(collectDto.getUserCode());
            nCollect.setCollectStatus(true);
            nCollect.setCollectTime(LocalDateTime.now());
        }else{
            collect.setCollectStatus(true);
            collectService.updateById(collect);

        }
        return Result.succ("收藏成功");
    }
    /**
     * 取消收藏
     * @param collectDto
     */
    @ApiOperation(value = "取消收藏",notes = "返回data:取消成功")
    @PostMapping("/cancel")
    public Result cancel(CollectDto collectDto){
        QueryWrapper<Collect>wrapper = new QueryWrapper<>();
        wrapper.eq("user_code", collectDto.getUserCode())
                .eq("post_code",collectDto.getPostCode());
        Collect collect = collectService.getOne(wrapper);
        collect.setCollectStatus(false);
        collectService.updateById(collect);
        return Result.succ("取消成功");
    }
}
