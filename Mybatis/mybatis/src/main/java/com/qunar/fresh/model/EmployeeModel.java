package com.qunar.fresh.model;

import com.qunar.fresh.enums.Gender;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by kingsley.zhang on 2017/3/21.
 */
public class EmployeeModel implements Serializable {
    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 员工工号
     */
    @NotEmpty
    private Integer staffId;
    /**
     * 中文姓名
     */
    @NotEmpty
    private String name;
    /**
     * 电话号码
     */
    private String mobile;
    /**
     * 工作地区，例如北京、上海等
     */
    private String area;
    /**
     * 性别，1 男2 女
     */
    private Gender gender;
    /**
     * '1:在职2：离职',
     */
    private Integer isValid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    @Override
    public java.lang.String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
