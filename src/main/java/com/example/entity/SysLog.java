package com.example.entity;

import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable {
    /**
     * 主键
     */
    private Integer logId;
    /**
     * 标题
     */
    private String title;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 方法
     */
    private Integer businessType;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 操作时间
     */
    private Date operationTime;
    /**
     * url地址
     */
    private String operationUrl;
    /**
     * 返回结果
     */
    private String jsonResult;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 错误信息
     */
    private String errorMessge;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationUrl() {
        return operationUrl;
    }

    public void setOperationUrl(String operationUrl) {
        this.operationUrl = operationUrl;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    public String getErrorMessge() {
        return errorMessge;
    }

    public void setErrorMessge(String errorMessge) {
        this.errorMessge = errorMessge;
    }
}
