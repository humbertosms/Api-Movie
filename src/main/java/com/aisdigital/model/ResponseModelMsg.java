package com.aisdigital.model;

import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;

@ApiModel
public class ResponseModelMsg {
    private List<String> statusMsg;
    private int codeMsg;
    private Object data;

    public ResponseModelMsg(String statusMsg, int codeMsg) {
        this.statusMsg = new ArrayList<>();
        this.statusMsg.add(statusMsg);
        this.codeMsg = codeMsg;
    }

    public ResponseModelMsg(String statusMsg, int codeMsg, Object data) {
        this.statusMsg = new ArrayList<>();
        this.statusMsg.add(statusMsg);
        this.codeMsg = codeMsg;
        this.data = data;
    }

    public ResponseModelMsg() {

    }

    public List<String> getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(List<String> statusMsg) {
        this.statusMsg = statusMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(int codeMsg) {
        this.codeMsg = codeMsg;
    }
}
