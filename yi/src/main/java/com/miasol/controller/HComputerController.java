package com.miasol.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.db.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.miasol.common.dto.HComputerDto;
import com.miasol.common.lang.Result;
import com.miasol.entity.HComputer;
import com.miasol.service.HComputerService;
import com.miasol.service.impl.HComputerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/h-computer")
@Api(tags = "硬件 笔记本相关接口")
public class HComputerController {


    @Autowired
    HComputerService hComputerService;

    /**
     * 获取所有信息列表
     */
    @ApiOperation(value = "获取所有信息列表",notes = "返回data：list")
    @GetMapping("/getAll")
    public Result getAll(){
        List<HComputer> list = hComputerService.list();
        return Result.succ(list);
    }

    /**
     * 获取指定名称的价格
     *
     */
    @ApiOperation(value = "获取指定名称的价格",notes = "返回data：<k,v>")
    @GetMapping("/getPrice")
    public Result getPrice(@ApiParam("笔记本名称") String name){
        QueryWrapper<HComputer> wrapper= new QueryWrapper<>();
        wrapper.select("name","price").eq("name",name);
        HComputer hComputer = hComputerService.getOne(wrapper);
        return Result.succ(MapUtil.builder().put("name", hComputer.getPrice()));
    }

    /**
     * 模糊查询
     */
    @ApiOperation(value = "模糊查询",notes = "返回data：list")
    @GetMapping("/getListByLikeName")
    public Result getListByLikeName(@ApiParam("模糊查询输入的内容") String str){
        QueryWrapper<HComputer> wrapper= new QueryWrapper<>();
        wrapper.like("name",str);
        List<HComputer> list = hComputerService.list(wrapper);
        return Result.succ(list);
    }

}
