package com.qunar.fresh.dao;


import com.qunar.fresh.model.HolidayModel;
import com.qunar.fresh.model.LeaveHolidayModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by kingsley.zhang on 2017/3/21.
 */
@Repository
public interface LeaveHolidayDao {
    /**
     * 查询员工请假信息
     * @param staffId
     * @param type
     * @return
     */
    List<LeaveHolidayModel> selectLeaveHolidayById(@Param("staffId") int staffId, @Param("type") Integer type);

    /**
     * 批量删除员工请假信息
     * @param staffIdList
     * @return
     */
    int batchDeleteLeaveHoliday(List<Integer> staffIdList);


    /**
     * 插入员工请假信息
     * @param leaveHoliday
     * @return
     */
    int insertLeaveHoliday(LeaveHolidayModel leaveHoliday);
}
