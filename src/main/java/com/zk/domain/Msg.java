package com.zk.domain;

import java.util.List;

public class Msg {
    private Integer status;
    private String msg;
    private List data;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        if (status==null||status==0){
            status=200;
        }
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        if (msg==null||msg.equals("")){
            msg="响应成功";
        }
        this.msg = msg;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
