package com.program.haohu.controller;


import java.util.HashMap;

public class AjaxResult extends HashMap {

    public AjaxResult(int code, String msg) {
        super.put("code",code);
        super.put("msg",msg);
    }

    public AjaxResult() {
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
