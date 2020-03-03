package com.alan.project.exception;

public enum  CustomizeErrorCode {
    FILE_UPLOAD_FAILD(501,"文件上传失败");

    private Integer code;
    private String message;


    CustomizeErrorCode(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
