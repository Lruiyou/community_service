package com.alan.project.enums;

public enum ResultCode {
     SUCCESS(200,"成功"),
    TOKEN_NULL(501,"token为空"),
    IMAGE_SIZE_ERROR(401,"上传的图片宽度和长度要小于600"),
    QUESTION_NOT_EXIT(402,"问题不存在，请刷新页面"),
    COMMENT_NOT_EXIT(403,"评论不存在，请刷新页面"),
    ERROR(400,"失败");

    private Integer code;

    private String msg;

    ResultCode(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static Integer getCode(String msg){
        for (ResultCode item : ResultCode.values()){
            if (item.getMsg().equals(msg)) {
                return item.getCode();
            }
        }
        return null;
    }

    public static String getMsg(Integer code) {
        for (ResultCode item : ResultCode.values()) {
            if (item.getCode().equals(code)) {
                return item.getMsg();
            }
        }
        return "";
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
