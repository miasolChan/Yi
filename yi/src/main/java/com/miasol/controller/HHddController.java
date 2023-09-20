package com.miasol.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miasol.common.lang.Result;
import com.miasol.entity.HHdd;
import com.miasol.service.HHddService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/h-hdd")
@Api(tags = "硬件 机械硬盘相关接口")
public class HHddController {
    @Autowired
    HHddService hHddService;
    /**
     * 获取所有列表
     */
    @ApiOperation(value = "获取所有信息列表",notes = "返回data：list")
    @GetMapping("/getAll")
    public Result getAll(){
        List<HHdd> list = hHddService.list();
        return Result.succ(list);
    }
    /**
     * 获取价格
     */
    @ApiOperation(value = "获取指名称机械硬盘的价格",notes = "返回data：<k,v>")
    @GetMapping("/getPrice")
    public Result getPrice(@ApiParam("固态硬盘名称")String name){
        QueryWrapper<HHdd> wrapper = new QueryWrapper<>();
        wrapper.select("name","price").eq("name",name);
        List<HHdd> list = hHddService.list(wrapper);
        return Result.succ(list);
    }

    /**
     * 根据硬件名查询所有内容
     */
    @ApiOperation(value = "根据硬件名查询所有内容",notes = "返回data：list")
    @GetMapping("/getDetailByName")
    public Result getDetailByName(@ApiParam("固态硬盘名称")String name){
        QueryWrapper<HHdd> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        List<HHdd> list = hHddService.list(wrapper);
        return  Result.succ(list);
    }


    /**
     * 模糊查询
     */
    @ApiOperation(value = "模糊查询",notes = "返回data：list")
    @GetMapping("/getListByLikeName")
    public Result getListByLikeName(@ApiParam("模糊查询内容") String str){
        QueryWrapper<HHdd> wrapper= new QueryWrapper<>();
        wrapper.like("name",str);
        List<HHdd> list = hHddService.list(wrapper);
        return Result.succ(list);
    }
}
