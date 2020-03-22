package com.alan.project.enums;

public enum  NotificationType {
    QUESTION(1,"回复了问题"),
    COMMENT(2,"回复了评论")
    ;

    private int type;
    private String msg;

    NotificationType(int type,String msg) {
        this.type = type;
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    public static String nameOfType(int type){
        for (NotificationType notificationType : NotificationType.values()){
            if (notificationType.getType() == type){
                return notificationType.getMsg();
            }
        }
        return "";
    }
}
