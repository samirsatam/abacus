package com.abacus.resources.result;

import com.abacus.resources.data.IData;

public class Result implements IData {
    private String message;
    public Result(String message) {
        this.message = message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }

    public String getType() {
        return "result";
    }

    public long getId() {
        return -1;
    }

    public void setType(String dataType) {
        this.setType("result");
    }
}
