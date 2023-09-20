package com.miasol.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miasol.common.lang.Result;
import com.miasol.entity.HComputer;
import com.miasol.entity.HCpu;
import com.miasol.service.HCpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/h-cpu")
@Api(tags = "硬件 CPU相关接口")
public class HCpuController {

    @Autowired
    HCpuService hCpuService;
    /**
     * 获取所有列表
     */
    @ApiOperation(value = "获取所有信息列表",notes = "返回data：list")
    @GetMapping("/getAll")
    public Result getAll(){
        List<HCpu> list = hCpuService.list();
        return Result.succ(list);
    }
    /**
     * 获取价格
     */
    @ApiOperation(value = "获取指名称CPU的价格",notes = "返回data：<k,v>")
    @GetMapping("/getPrice")
    public Result getPrice(@ApiParam("CPU名称") String name){
        QueryWrapper<HCpu> wrapper = new QueryWrapper<>();
        wrapper.select("name","price").eq("name",name);
        HCpu hCpu = hCpuService.getOne(wrapper);
        return Result.succ(MapUtil.builder().put("name", hCpu.getPrice()));

    }

    /**
     * 根据硬件名查询所有内容
     */
    @ApiOperation(value = "根据硬件名查询所有内容",notes = "返回data：list")
    @GetMapping("/getDetailByName")
    public Result getDetailByName(@ApiParam("CPU名称")String name){
        QueryWrapper<HCpu> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        List<HCpu> list = hCpuService.list(wrapper);
        return Result.succ(list);
    }


    /**
     * 模糊查询
     */
    @ApiOperation(value = "模糊查询",notes = "返回data：list")
    @GetMapping("/getListByLikeName")
    public Result getListByLikeName(@ApiParam("模糊查询内容") String str){
        QueryWrapper<HCpu> wrapper= new QueryWrapper<>();
        wrapper.like("name",str);
        List<HCpu> list = hCpuService.list(wrapper);
        return Result.succ(list);
    }
}
