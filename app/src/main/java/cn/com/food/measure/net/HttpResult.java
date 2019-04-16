package cn.com.food.measure.net;

import java.util.List;

import cn.com.food.measure.bean.BusinessBean;

/**
 * Description ：网络请求返回结果
 * creator ：但子龙
 * Create time : 2018/4/23
 */

public class HttpResult {
    private int Code;
    private String Message;
    private List<Object> Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<Object> getData() {
        return Data;
    }

    public void setData(List<Object> data) {
        Data = data;
    }
}
