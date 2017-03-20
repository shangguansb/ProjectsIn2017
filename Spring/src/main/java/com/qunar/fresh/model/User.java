package com.qunar.fresh.model;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * Created by kingsley.zhang on 17-3-16.
 */
public class User implements Serializable {

    private Integer id;
    private String username;
    private Integer isValid;
    private Integer age;
    private Integer gender;
    private String remark;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private DateTime registerTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private DateTime lastModifyTime;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public DateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(DateTime registerTime) {
        this.registerTime = registerTime;
    }

    public DateTime getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(DateTime lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public String toString() {
        return User.class + " { " + id + "," + username + "," + age + "," + gender + "," + isValid + "," + registerTime + "," + lastModifyTime + " }";
    }
}
