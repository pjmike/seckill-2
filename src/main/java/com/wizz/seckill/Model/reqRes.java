package com.wizz.seckill.Model;

public class reqRes {
    private String result;
    private String des;

    public reqRes(String result, String des) {
        this.result = result;
        this.des = des;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getResult() {
        return result;
    }

    public String getDes() {
        return des;
    }
}
