package com.miasol.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miasol.common.dto.UserDto;
import com.miasol.common.lang.Result;
import com.miasol.entity.User;
import com.miasol.service.UserService;
import com.miasol.utils.JwtUtils;
import com.miasol.utils.UuidUtils;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author miasol
 *
 * 账号管理控制器
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户 相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 账号登录 - 接口
     * @param accountDto
     * @param response
     * @return
     */
    @ApiOperation(value="账号登录",notes = "返回data : <k,v>(userCode和username)")
    @GetMapping("/login")
    public Result index(@Validated UserDto accountDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>()
                                               .eq("username", accountDto.getUsername())
                                               .eq("password",accountDto.getPassword()));

        Assert.notNull(user, "用户不存在");
        String jwt = jwtUtils.generateToken(user);
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        return Result.succ(MapUtil.builder()
                                   .put("userCode", user.getUserCode())
                                   .put("username", user.getUsername()).map());
    }


    /**
     * 账号注册 - 接口
     * @param username
     * @param mobile
     * @param password
     * @return
     */
    @ApiOperation(value="用户登录",notes = "返回data : 登录成功")
    @PostMapping("/register")
    public Result register(@ApiParam("用户名") String username,@ApiParam("手机号") String mobile, @ApiParam("密码") String password, @ApiParam("请求") HttpServletResponse response) {
        password = SecureUtil.md5(password);
        String userCode = UuidUtils.generateShortUuid();
        User user = new User(null,username,password,mobile,null,null,userCode);
        userService.save(user);
        String jwt = jwtUtils.generateToken(user);
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        return Result.succ("用户注册成功");
    }

    /**
     * 身份鉴权 - 接口
     * @param tokenid
     * @return
     */
    @ApiOperation(value="身份鉴权token",notes = "返回data : mobile 电话号码")
    @GetMapping("/getToken")
    public Result getToken(@ApiParam("用户的Token") String tokenid) {
        JwtUtils jwtUtils = new JwtUtils();
        Claims claimByToken = jwtUtils.getClaimByToken(tokenid);
        String mobile = (String) claimByToken.get("mobile");
        return Result.succ(mobile);
    }
    /**
     * 头像上传
     *
     */
    @ApiOperation(value="头像上传（可以不实现）",notes = "返回data : 上传失败/成功")
    @PostMapping("/imageUpload")
    public Result imageUpload(MultipartFile file, HttpServletRequest request, HttpSession session){
        String fileName = file.getOriginalFilename();
        String suffix = null;
        if (fileName != null) {
            suffix = fileName.substring(fileName.lastIndexOf("."));
        }
        String src = request.getServletContext().getRealPath("file/user/avatar/");
        String uuid = UuidUtils.getUUID();
        String name = uuid+suffix;
        File dest = new File(src+"/"+name);
        String imgPath = "file/user/avatar/" +name;
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), dest);
            return Result.succ(MapUtil.builder().put("src", imgPath).put("fileName",name));
        } catch (IOException e)
        {
            return Result.fail("图片上传失败");

        }
    }


    /**
     * 通过用户名获取userCode
     */
    @ApiOperation(value="通过用户名获取userCode",notes = "返回data : <k,v> userCode")
    @GetMapping("/getCodeByName")
    public Result getCodeByName(@ApiParam("用户名") String name){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("user_code").eq("username",name);
        User user= userService.getOne(wrapper);
        return Result.succ(MapUtil.builder().put("userCode",user.getUserCode()));
    }


}
