package com.qunar.fresh.dao;

import com.qunar.fresh.model.EmployeeModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by kingsley.zhang on 2017/3/21.
 */
@Repository
public interface EmployeeDao {
    /**
     * 保存新入职员工，返回1保存成功，返回0保存失败
     *
     * @param Employee 员工
     * @return 0保存失败，1保存成功
     */
    int saveEmployee(EmployeeModel Employee);

    /**
     * 批量插入员工信息
     * @param employees 员工信息集合
     * @return 影响行数
     */
    int batchInsertEmployee(List<EmployeeModel> employees);

    /**
     * 通过id查询员工信息
     * @param staffId
     * @return
     */
    EmployeeModel selectEmployeeById(Integer staffId);

    /**
     * 批量删除员工信息
     * @param staffList 员工id集合
     * @return
     */
    int batchDeleteEmployee(List<Integer> staffList);


    /**
     * 通过名字查询员工信息
     * @param name
     * @return
     */
    List<EmployeeModel> selectEmployeeByName(String name);

    /**
     * 批量查询员工信息
     * @param offset 偏移
     * @param size 容量
     * @param params 参数(预留)
     * @return
     */
    List<EmployeeModel> batchSelectEmployee(@Param("offset") Integer offset, @Param("size") Integer size, @Param("params") String...params);


    /**
     * 通过员工状态查询员工信息
     * @param type
     * @return
     */
    List<EmployeeModel> selectEmployeeByState(Integer type);
}
