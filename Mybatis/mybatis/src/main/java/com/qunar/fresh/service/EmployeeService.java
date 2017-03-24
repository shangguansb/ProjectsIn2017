package com.qunar.fresh.service;

import com.qunar.fresh.model.EmployeeModel;
import com.qunar.fresh.model.HolidayModel;
import com.qunar.fresh.model.LeaveHolidayModel;

import java.util.List;

/**
 * Created by kingsley.zhang on 2017/3/21.
 */
public interface EmployeeService {

    int saveEmployee(EmployeeModel employeeModel);

    /**
     * 批量插入员工信息
     *
     * @param employees 员工 List
     * @return boolean
     * @throws Exception 触发事务
     */

    /**
     * 批量插入员工信息
     * @param employees 员工 List
     * @return boolean
     * @throws Exception 触发事务
     */
    boolean batchInsertEmployee(List<EmployeeModel> employees)throws Exception;


    /**
     * 根据员工id查询员工信息
     * @param staffId
     * @return
     */
    EmployeeModel queryEmployeeById(int staffId);


    /**
     * 按照名字查询员工信息
     * @param name
     * @return
     */
    List<EmployeeModel> queryEmployeeByName(String name);


    /**
     * 统计名字查找的人数
     * @param name
     * @return
     */
    int countEmployeeLikeName(String name);


    /**
     * 删除离职员工信息
     * @return
     */
    boolean deleteNonValidEmployee()throws Exception;




    /**
     * 更新员工的假期信息
     * @param holiday
     * @return
     */
    boolean updateHoliday(HolidayModel holiday);

    /**
     * 批量查询员工的信息
     * @param offset 偏移量
     * @param size 容量
     * @return List
     */
    List<EmployeeModel> batchQueryEmployee(int offset,int size);


    /**
     * 查询员工的请假信息
     * @param staffId
     * @return
     */
    List<LeaveHolidayModel> queryLeaveHolidayById(int staffId);


    /**
     * 统计请假天数少于某天的员工id
     * @param num 数量
     * @param type 请假类型
     * @return 员工id集合
     */
    List<Integer> queryAlreadyHolidayDayLessThanNum(int num,int type);

    /**
     * 统计员工已请年假天数
     * @param staffId 员工id
     * @param type 假期类型
     */
    int countHoliday(int staffId,int type);


    /**
     * 查询假期信息
     * @param staffId 员工id
     * @return
     */
    HolidayModel queryHolidayById(int staffId);



    /**
     * 处理员工的请假信息
     * @param leaveHoliday 请假信息表
     * @return 是否成功
     */
    boolean processHolidayRequest(LeaveHolidayModel leaveHoliday);
}