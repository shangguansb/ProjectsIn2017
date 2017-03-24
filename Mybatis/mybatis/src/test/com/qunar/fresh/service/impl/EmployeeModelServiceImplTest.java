package com.qunar.fresh.service.impl;

import com.google.common.collect.Lists;
import com.qunar.fresh.Constants.EmployeeConstant;
import com.qunar.fresh.enums.Gender;
import com.qunar.fresh.model.EmployeeModel;
import com.qunar.fresh.model.HolidayModel;
import com.qunar.fresh.model.LeaveHolidayModel;
import com.qunar.fresh.service.EmployeeService;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class EmployeeModelServiceImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeModelServiceImplTest.class);

    @Autowired
    EmployeeService employeeService;

    @Test
    public void testSaveEmployee() throws Exception {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setStaffId(8);
        employeeModel.setName("李磊");
        employeeModel.setMobile("15941515");
        employeeModel.setIsValid(1);
        employeeModel.setArea("北京");
        employeeModel.setGender(Gender.FEMALE);
        employeeModel.setId((int) Math.random() * 1000);
        Assert.assertEquals(employeeService.saveEmployee(employeeModel)
                , 1);
    }


    @Test
    public void queryEmployeeInfoById() throws Exception {
        int staffId = 1;
        EmployeeModel employee = employeeService.queryEmployeeById(staffId);
        LOGGER.info(employee.toString());
    }


    /*3 查询2号员工的请假详单*/
    @Test
    public void queryEmployeeLeaveHolidayInfoById() throws Exception {
        int staffId = 2;
        List<LeaveHolidayModel> leaveHolidays =
                employeeService.queryLeaveHolidayById(staffId);
        LOGGER.info(leaveHolidays.toArray().toString());
    }


    /* 更新员工的假期信息*/
    @Test
    public void updateEmployeeHoliday() throws Exception {
        HolidayModel holiday = new HolidayModel();
        holiday.setSickNum(3);
        holiday.setStaffId(3);
        Assert.assertEquals(employeeService.updateHoliday(holiday), true);
    }


    /*删除已经离职的员工*/
    @Test
    public void deleteEmployeeInfo() throws Exception {
        Assert.assertEquals(employeeService.deleteNonValidEmployee()
                , true);
    }

    /*查询姓张的在职员工总数*/
    @Test
    public void queryEmployeeInfoByName() throws Exception {
        String name = "张";
        int count = employeeService.countEmployeeLikeName(name);
        LOGGER.info(count + "");

    }

    /*查询请年假天数小于3天的员工id号*/
    @Test
    public void queryAlreadyHolidayDayLessThanNum() throws Exception {
        int num = 3;
        int type = EmployeeConstant.ANNUAL_HOLIDAY_TYPE;
        List<Integer> list = employeeService.queryAlreadyHolidayDayLessThanNum(num, type);
        LOGGER.info(list.toString());
    }

    /*分别查询按照工号排名的前1-3名员工和4-6名员工详情（用分页实现）*/
    @Test
    public void batchQueryEmployeeInfo() throws Exception {
        List<EmployeeModel> employeeList = employeeService.batchQueryEmployee(0, 3);
        LOGGER.info(employeeList.toString());
        List<EmployeeModel> employeeList1 = employeeService.batchQueryEmployee(3, 3);
        LOGGER.info(employeeList1.toString());
    }


    @Test
    public void batchInsertEmployeeInfo() throws Exception {
        List<EmployeeModel> employeeList = Lists.newArrayList();
        for (int i = 10; i < 20; i++) {
            EmployeeModel employee = new EmployeeModel();
            employee.setName("杨桥" + i);
            employee.setStaffId(i);
            employee.setGender(Gender.MALE);
            employee.setArea("北京");
            employee.setMobile("12311231112");
            employeeList.add(employee);
        }
        Assert.assertEquals(employeeService.batchInsertEmployee(employeeList)
                , true);
    }


    @Test
    public void countEmployeeHolidayInfo() throws Exception {
        int staffId = 1;
        int alreadyDayNum = employeeService.countHoliday(staffId, 1);
        HolidayModel holiday = employeeService.queryHolidayById(staffId);
        LOGGER.info(alreadyDayNum + "  " + holiday.getAnnualNum());
    }

    @Test
    public void doHolidayRequest() throws ParseException {
        /*构建请求信息*/
        LeaveHolidayModel leaveHoliday = new LeaveHolidayModel();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        /*请三天假*/
        leaveHoliday.setStartDate(simpleDateFormat.parse("2017-03-28 12:21:12"));
        leaveHoliday.setEndDate(simpleDateFormat.parse("2017-03-31 21:21:21"));
        DateTime startDate = new DateTime(leaveHoliday.getStartDate());
        DateTime endDate = new DateTime(leaveHoliday.getEndDate());
        Days days = Days.daysBetween(startDate, endDate);
        leaveHoliday.setDayNum(days.getDays());
        leaveHoliday.setStaffId(1);
        leaveHoliday.setType(1);
        leaveHoliday.setArea("北京");
        Assert.assertEquals(employeeService.processHolidayRequest(leaveHoliday), true);

    }


}
