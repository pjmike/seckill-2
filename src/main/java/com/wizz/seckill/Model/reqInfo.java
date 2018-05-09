package com.wizz.seckill.Model;


public class reqInfo {

    private String name;
    private String stuId;
    private String phoneNum;
    //example : 2018/5/8 23:03 -> 201805082303
    private Long time;
    //0 for success, 1 for fail
    private int State;


    public reqInfo() {
    }

    public reqInfo(String name, String stuId, String phoneNum, Long time, int state) {
        this.name = name;
        this.stuId = stuId;
        this.phoneNum = phoneNum;
        this.time = time;
        State = state;
    }

    public String getName() {
        return name;
    }

    public String getStuId() {
        return stuId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Long getTime() {
        return time;
    }

    public int getState() {
        return State;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setState(int state) {
        State = state;
    }
}
