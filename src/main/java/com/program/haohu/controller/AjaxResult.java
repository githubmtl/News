package com.program.haohu.controller;


import java.util.HashMap;

public class AjaxResult extends HashMap {
    private int code;
    private String msg;

    public AjaxResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResult() {
    }

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

    public static AjaxResult error(){
        return error("处理失败！");
    }

    public static AjaxResult error(String msg){
        return new AjaxResult(1,msg);
    }

    public static AjaxResult success(){
        return success("处理成功！");
    }

    public static AjaxResult success(String msg){
        return new AjaxResult(0,msg);
    }
}
