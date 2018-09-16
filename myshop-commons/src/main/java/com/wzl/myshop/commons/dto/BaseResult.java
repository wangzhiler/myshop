package com.wzl.myshop.commons.dto;

import java.io.Serializable;

/**
 * Created by thinkpad on 2018/9/9.
 */
public class BaseResult implements Serializable {
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;

    private int status;
    private String message;

    @Override
    public String toString() {
        return "BaseResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }

    private static BaseResult createBaseResult(int status, String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        return baseResult;
    }

    public static BaseResult success() {
        return createBaseResult(STATUS_SUCCESS, "成功");
    }

    public static BaseResult success(String message) {
        return createBaseResult(STATUS_SUCCESS, message);
    }

    public static BaseResult fail() {
        return createBaseResult(STATUS_FAIL, "失败");
    }

    public static BaseResult fail(String message) {
        return createBaseResult(STATUS_FAIL, message);
    }

    public static BaseResult fail(int status, String message) {
        return createBaseResult(status, message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

























