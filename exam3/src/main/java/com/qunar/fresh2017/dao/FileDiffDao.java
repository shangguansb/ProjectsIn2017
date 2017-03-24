package com.qunar.fresh2017.dao;

import com.qunar.fresh2017.model.FileDiff;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kingsley.zhang on 2017/3/23.
 * dao层
 */
@Repository
public interface FileDiffDao {

    /*
     * 将数据插入到数据库
     */
    public int saveFileDiff(FileDiff fileDiff);

    /*
     * 查询数据库中表的总行数
     */
    public int querySum();

    /*
     * 分页查询历史文件差异记录
     */
    public List<FileDiff> queryFileDiff(RowBounds rowBounds);

    /*
     * 删除指定的历史文件差异记录
     */
    public int deleteById(int id);
}
