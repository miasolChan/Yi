package com.miasol.common.lang;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * Resful数据返回格式统一处理类
 */
@ApiModel("封装结果集")
public class Result implements Serializable {

    @ApiModelProperty("code,200正常，非200异常")
    private int code;
    @ApiModelProperty("消息")
    private String msg;
    @ApiModelProperty("返回值")
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result succ(Object data) {
        return succ(200, "成功", data);
    }

    private static Result succ(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result fail(String mess) {
        return fail(400, mess,null);
    }

    public static Result fail(int code, String msg, Object data) {
        Result m = new Result();
        m.setCode(code);
        m.setMsg(msg);
        m.setData(data);
        return m;
    }
}
