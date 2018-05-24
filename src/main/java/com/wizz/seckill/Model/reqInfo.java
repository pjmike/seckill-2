package com.wizz.seckill.Model;


import java.sql.Timestamp;

public class reqInfo {

    private String stuName;
    private String stuId;
    private String phoneNum;
    //0 for success, 1 for fail
    private int state;
    private Timestamp time;

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStuName() {

        return stuName;
    }

    public String getStuId() {
        return stuId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getState() {
        return state;
    }


    public reqInfo() {
    }

    public reqInfo(String stuName, String stuId, String phoneNum, int state) {
        this.stuName = stuName;
        this.stuId = stuId;
        this.phoneNum = phoneNum;
        this.state = state;
    }

    @Override
    public String toString() {
        return "reqInfo{" +
                "stuName='" + stuName + '\'' +
                ", stuId='" + stuId + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", state=" + state +
                ", time=" + time +
                '}';
    }
}
