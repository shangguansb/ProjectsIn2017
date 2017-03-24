package com.qunar.fresh.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by kingsley.zhang on 2017/3/21.
 */
public class HolidayModel {
    private Integer id;
    private Integer staffId;
    private Integer sickNum;

    private Integer annualNum;


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

    public Integer getSickNum() {
        return sickNum;
    }

    public void setSickNum(Integer sickNum) {
        this.sickNum = sickNum;
    }

    public Integer getAnnualNum() {
        return annualNum;
    }

    public void setAnnualNum(Integer annualNum) {
        this.annualNum = annualNum;
    }
    @Override
    public java.lang.String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
