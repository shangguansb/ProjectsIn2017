package com.qunar.fresh2017.service.impl;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.qunar.fresh2017.dao.FileDiffDao;
import com.qunar.fresh2017.model.FileDiff;
import com.qunar.fresh2017.service.UploadFileService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by kingsley.zhang on 2017/3/23.
 * 服务层，关于上传文件功能的服务
 */
@Service("uploadFileService")
public class UploadFileServiceImpl implements UploadFileService {

    @Resource
    private FileDiffDao fileDiffDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileServiceImpl.class);
    private static final String FILEPATH = UploadFileServiceImpl.class.getResource("/").getPath(); // 文件存放目录
    private String sourcePath; // 源文件存放地址
    private String targetPath; // 目标文件存放地址

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    /*
     * 上传两个文件
     */
    @Override
    public void uploadFiles(String source, String target) throws IOException {
        sourcePath = source;
        targetPath = target;

    }

    /*
     * 将文件记录插入到数据库
     */
    @Override
    public int insert(FileDiff fileDiff) {
        return fileDiffDao.saveFileDiff(fileDiff);
    }

    /*
     * 对比两个文件并获得差别
     * @param FileDiff是模型类，里面已经记录了两个文件的内容
     * @returnType MapDifference是Maps.defference方法的比较结果，是两个map的比较结果
     */
    @Override
    public MapDifference compareFiles(FileDiff fileDiff) throws IOException {
        Map<String, String> sourceMap = Splitter.on("<br>").omitEmptyStrings().withKeyValueSeparator("=")
                .split(fileDiff.getSourceFile());
        Map<String, String> targetMap = Splitter.on("<br>").omitEmptyStrings().withKeyValueSeparator("=")
                .split(fileDiff.getTargetFile());
        return Maps.difference(sourceMap, targetMap);
    }

    /*
     * 将上传文件比较，并将文件差异记录保存到数据库中
     */
    @Transactional
    @Override
    public int compareAndSaveFileDiff() throws IOException {
        Preconditions.checkNotNull(sourcePath, "源文件路径为null");
        Preconditions.checkNotNull(targetPath, "目标文件路径为null");
        FileDiff fileDiff = new FileDiff();

        // 读入文件，并将字符串中的换行符替换为<br>
        fileDiff.setSourceFile(Files.toString(new File(sourcePath), Charsets.UTF_8).replace("\n", "<br>"));
        fileDiff.setTargetFile(Files.toString(new File(targetPath), Charsets.UTF_8).replace("\n", "<br>"));
        MapDifference mapDifference = compareFiles(fileDiff);
        fileDiff.setFileDiff(convertMapDifferenceToString(mapDifference));
        return insert(fileDiff);
    }

    /*
     * 查询历史文件差异记录的总数
     */
    @Override
    public int querySum() {
        return fileDiffDao.querySum();
    }

    /*
     * 分页查询历史文件差异记录
     */
    @Override
    public List<FileDiff> queryFileDiff(int start, int size) {
        return fileDiffDao.queryFileDiff(new RowBounds(start, size));
    }

    /*
     * 删除指定的历史文件差异记录
     */
    @Transactional
    @Override
    public int deleteById(int id) {
        return fileDiffDao.deleteById(id);
    }

    /*
     * 将MapDifference转化成String
     */
    private String convertMapDifferenceToString(MapDifference mapDifference) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> onLeft = mapDifference.entriesOnlyOnLeft();
        for (String key : onLeft.keySet()) {
            stringBuilder.append("-" + key + "=" + onLeft.get(key) + "<br>");
        }
        Map<String, String> onRight = mapDifference.entriesOnlyOnRight();
        for (String key : onRight.keySet()) {
            stringBuilder.append("+" + key + "=" + onRight.get(key) + "<br>");
        }
        Map<String, MapDifference.ValueDifference<String>> difference = mapDifference.entriesDiffering();
        for (String key : difference.keySet()) {
            stringBuilder.append("-" + key + "=" + difference.get(key).leftValue() + ";");
            stringBuilder.append("+" + key + "=" + difference.get(key).rightValue() + "<br>");
        }
        return stringBuilder.toString();
    }

}
