package com.qunar.fresh.dao;

import com.qunar.fresh.model.HolidayModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kingsley.zhang on 2017/3/21.
 */
@Repository
public interface HolidayDao {


    /**
     * 更新员工假期信息
     *
     * @param holiday
     * @return
     */
    int updateHolidayById(HolidayModel holiday);

    /**
     * 查询少于某个值的员工请假id
     *
     * @param num
     * @param type
     * @return
     */
    List<Integer> selectHolidayDayLessThanNum(@Param("num") int num, @Param("type") Integer type);

    /**
     * 查询员工假期信息
     *
     * @param staffId
     * @return
     */
    HolidayModel selectHoliday(@Param("StaffId") Integer staffId);

    /**
     * 插入员工假期信息
     *
     * @param holiday
     * @return
     */
    int insertHoliday(HolidayModel holiday);

    /**
     * 批量插入员工假期信息
     *
     * @param holidays
     * @return
     */
    int batchInsertHoliday(List<HolidayModel> holidays);

    /**
     * 批量删除员工假期信息
     *
     * @param staffIdList
     * @return
     */
    int batchDeleteHoliday(List<Integer> staffIdList);


    /**
     * 更新员工假期中的年假信息
     *
     * @param staffId
     * @param num
     * @return
     */
    int updateAnnualNum(@Param("staffId") int staffId, @Param("num") int num);
}