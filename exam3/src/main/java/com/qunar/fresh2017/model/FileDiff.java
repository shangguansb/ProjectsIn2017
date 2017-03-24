package com.qunar.fresh2017.model;

import java.util.Date;

/**
 * Created by kingsley.zhang on 2017/3/23.
 * 模型层
 */
public class FileDiff {
    private int id; // 主键id
    private Date diffTime; // 对比文件的时间
    private String sourceFile; // 源文件内容
    private String targetFile; // 目标文件内容
    private String fileDiff; // 文件差异内容

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDiffTime() {
        return diffTime;
    }

    public void setDiffTime(Date diffTime) {
        this.diffTime = diffTime;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(String targetFile) {
        this.targetFile = targetFile;
    }

    public String getFileDiff() {
        return fileDiff;
    }

    public void setFileDiff(String fileDiff) {
        this.fileDiff = fileDiff;
    }
}
