package com.example.exception;

public enum CustomerExcepitonType {
    USER_INSTER_ERROR("用户输入错误",400),
    SYSTEM_ERROR("系统错误",500),
    UNKNOWN_ERROR("未知错误",111),
    UserNotExist("认证异常",222);
    private String description;
    private int code;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    CustomerExcepitonType(String description, int code) {
        this.description = description;
        this.code = code;
    }
}
