package com.alan.project.enums;

public enum ResultCode {
     SUCCESS(200,"成功"),
    TOKEN_NULL(501,"token为空"),
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
