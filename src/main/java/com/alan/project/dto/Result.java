package com.alan.project.dto;

import com.alan.project.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private Integer code;

    private String msg;

    private Object data;


    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }


    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }


    public void setResultCode(ResultCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }
}
