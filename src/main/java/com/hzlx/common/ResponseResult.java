package com.hzlx.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 小马不会跑
 * @version 1.0.0
 * @title ResponseResult
 * @description <TODO description class purpose>
 * @createTime 2023/8/17 11:37
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult implements Serializable {
    @JSONField(serialize = false)
    private static final long serialVersionUID = -8019568988368241114L;

    private Integer code;
    private String message;
    private Object data;
    private Integer total;

    public static ResponseResult success(){
        return new ResponseResult(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),null,null);
    }

    public static ResponseResult success(Object data){
        return new ResponseResult(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),data,null);
    }
    public static ResponseResult success(Object data,Integer total){
        return new ResponseResult(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),data,total);
    }
    public static ResponseResult error(ResultEnum resultEnum){
        return new ResponseResult(resultEnum.getCode(),resultEnum.getMessage(),null,null);
    }
}

