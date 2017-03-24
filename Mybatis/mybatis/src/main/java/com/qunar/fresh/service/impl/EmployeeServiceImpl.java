package com.qunar.fresh.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.qunar.fresh.Constants.EmployeeConstant;
import com.qunar.fresh.dao.EmployeeDao;
import com.qunar.fresh.dao.HolidayDao;
import com.qunar.fresh.dao.LeaveHolidayDao;
import com.qunar.fresh.model.EmployeeModel;
import com.qunar.fresh.model.HolidayModel;
import com.qunar.fresh.model.LeaveHolidayModel;
import com.qunar.fresh.service.EmployeeService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kingsley.zhang on 2017/3/21.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Resource
    EmployeeDao employeeDao;
    @Resource
    HolidayDao holidayDao;
    @Resource
    LeaveHolidayDao leaveHolidayDao;

    public int saveEmployee(EmployeeModel employeeModel) {
        Preconditions.checkNotNull(employeeModel, "员工信息不能为空");
        employeeDao.saveEmployee(employeeModel);
        return 1;
    }

    @Override
    @Transactional
    public boolean batchInsertEmployee(List<EmployeeModel> employees) throws Exception {
        if (CollectionUtils.isEmpty(employees) || employeeDao.batchInsertEmployee(employees) != employees.size()) {
            throw new Exception("添加员工信息错误");
        }
        /*初始化员工假期的信息*/
        List<HolidayModel> holidayList = Lists.newArrayList();
        for (EmployeeModel employee : employees) {
            HolidayModel holiday = new HolidayModel();
            holiday.setStaffId(employee.getStaffId());
            holiday.setSickNum(EmployeeConstant.EMPLOYEE_DEFAULT_SICK_NUM);
            holiday.setAnnualNum(EmployeeConstant.EMPLOYEE_DEFAULT_ANNUAL_NUM);
            holidayList.add(holiday);
        }
        if (CollectionUtils.isEmpty(holidayList) || holidayDao.batchInsertHoliday(holidayList) != holidayList.size()) {
            throw new Exception("添加假期信息错误");
        }
        return true;
    }


    @Override
    public EmployeeModel queryEmployeeById(int staffId) {
        return employeeDao.selectEmployeeById(staffId);
    }


    @Override
    public List<EmployeeModel> queryEmployeeByName(String name) {
        return employeeDao.selectEmployeeByName(name);
    }

    @Override
    public int countEmployeeLikeName(String name) {
        name = name + EmployeeConstant.FUZZY_QUERY_SYMBOL;
        List<EmployeeModel> employeeList = queryEmployeeByName(name);
        return employeeList != null ? employeeList.size() : 0;
    }


    @Override
    public List<EmployeeModel> batchQueryEmployee(int offset, int size) {
        return employeeDao.batchSelectEmployee(offset, size);
    }

    @Override
    @Transactional
    public boolean updateHoliday(HolidayModel holiday) {
        return holiday != null && holidayDao.updateHolidayById(holiday) > 0;

    }


    @Override
    public boolean deleteNonValidEmployee() throws Exception {
        List<EmployeeModel> employeeList = employeeDao.selectEmployeeByState(EmployeeConstant.EMPLOYEE_NOT_VAILD_SYMBOL);
        List<Integer> staffIdList = Lists.newArrayList();
        for (EmployeeModel employee : employeeList) {
            staffIdList.add(employee.getStaffId());
        }

        return deleteEmployeeAllInfo(staffIdList);
    }

    @Transactional
    private boolean deleteEmployeeAllInfo(List<Integer> staffIdList) throws Exception {
        if (CollectionUtils.isEmpty(staffIdList)) {
            return true;
        }
        if (employeeDao.batchDeleteEmployee(staffIdList) != staffIdList.size()) {
            throw new Exception("删除员工信息失败");
        }
        if (holidayDao.batchDeleteHoliday(staffIdList) != staffIdList.size()) {
            throw new Exception("删除假期信息是失败");
        }
        leaveHolidayDao.batchDeleteLeaveHoliday(staffIdList);
        return true;
    }


    @Override
    public List<LeaveHolidayModel> queryLeaveHolidayById(int staffId) {
        return leaveHolidayDao.selectLeaveHolidayById(staffId, null);
    }


    @Override
    public int countHoliday(int staffId, int type) {
        List<LeaveHolidayModel> leaveHolidays = leaveHolidayDao.selectLeaveHolidayById(staffId, type);
        int alreadyDayNum = 0;
        for (LeaveHolidayModel leaveHoliday : leaveHolidays) {
            if (leaveHoliday.getType() == type) {
                alreadyDayNum += leaveHoliday.getDayNum();
            }
        }
        return alreadyDayNum;
    }


    public List<Integer> queryAlreadyHolidayDayLessThanNum(int num, int type) {
        return holidayDao.selectHolidayDayLessThanNum(num, type);
    }

    @Override
    public HolidayModel queryHolidayById(int staffId) {
        return holidayDao.selectHoliday(staffId);
    }

    @Override
    @Transactional
    public boolean processHolidayRequest(LeaveHolidayModel leaveHoliday) {
        System.out.println(leaveHoliday);
        if (leaveHoliday == null || leaveHoliday.getStaffId() == null) {
            return false;
        }
        /*获取员工假期信息*/
        HolidayModel holiday = holidayDao.selectHoliday(leaveHoliday.getStaffId());
        if (holiday == null) {
            return false;
        }
        /*它给的表结构只能这样做*/
        int dayNum = 0;
        if (EmployeeConstant.ANNUAL_HOLIDAY_TYPE.equals(leaveHoliday.getType())) {
            dayNum = holiday.getAnnualNum();
        } else if (EmployeeConstant.SICK_HOLIDAY_TYPE.equals(leaveHoliday.getType())) {
            dayNum = holiday.getSickNum();
        }
        if (dayNum < leaveHoliday.getDayNum()) {
            return false;
        }

        if (leaveHolidayDao.insertLeaveHoliday(leaveHoliday) > 0
                && holidayDao.updateAnnualNum(leaveHoliday.getStaffId(), leaveHoliday.getDayNum()) > 0) {
            return true;
        }
        return false;

    }

}
