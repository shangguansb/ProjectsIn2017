package com.qunar.fresh2017.service;

import com.google.common.collect.MapDifference;
import com.qunar.fresh2017.model.FileDiff;

import java.io.IOException;
import java.util.List;

/**
 * Created by kingsley.zhang on 2017/3/23.
 * 服务层，关于上传文件功能的服务
 */
public interface UploadFileService {

    /*
     * 上传两个文件
     */
    public void uploadFiles(String source, String target) throws IOException;

    /*
     * 将文件记录插入到数据库
     */
    public int insert(FileDiff fileDiff);

    /*
     * 对比两个文件并获得差别
     * @param FileDiff是模型类，里面已经记录了两个文件的内容
     * @returnType MapDifference是Maps.defference方法的比较结果，是两个map的比较结果
     */
    public MapDifference compareFiles(FileDiff fileDiff) throws IOException;

    /*
     * 将上传文件比较，并将文件差异记录保存到数据库中
     */
    public int compareAndSaveFileDiff() throws IOException;

    /*
     * 查询历史文件差异记录的总数
     */
    public int querySum();

    /*
     * 分页查询历史文件差异记录
     */
    public List<FileDiff> queryFileDiff(int start, int size);

    /*
     * 删除指定的历史文件差异记录
     */
    public int deleteById(int id);
}
