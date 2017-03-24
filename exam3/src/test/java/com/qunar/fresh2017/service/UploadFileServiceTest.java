package com.qunar.fresh2017.service;

import com.google.common.collect.MapDifference;
import com.qunar.fresh2017.model.FileDiff;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml", "classpath:/mvc-dispatcher-servlet.xml"})
public class UploadFileServiceTest {

    @Resource
    private UploadFileService uploadFileService;

    @Test
    public void testCompareFiles() throws Exception {
        FileDiff fileDiff = new FileDiff();
        fileDiff.setSourceFile("a1=1");
        fileDiff.setTargetFile("a1=2");
        MapDifference mapDifference = uploadFileService.compareFiles(fileDiff);
        Assert.assertEquals(mapDifference != null, true);
    }

    @Test
    public void testInsert() throws Exception {
        FileDiff fileDiff = new FileDiff();
        fileDiff.setSourceFile("a1=1");
        fileDiff.setTargetFile("a2=1");
        fileDiff.setFileDiff("-a1=1;<br>+a2=1<br>");
        int inserts = uploadFileService.insert(fileDiff);
        Assert.assertEquals(inserts, 1);
    }

    @Test
    public void testQuerySum() throws Exception {
        int sum = uploadFileService.querySum();
        Assert.assertEquals(sum >= 0, true);
    }

    @Test
    public void testQueryFileDiff() throws Exception {
        FileDiff fileDiff = new FileDiff();
        fileDiff.setSourceFile("a1=1");
        fileDiff.setTargetFile("a2=1");
        fileDiff.setFileDiff("-a1=1;<br>+a2=1<br>");
        int inserts = uploadFileService.insert(fileDiff);
        List<FileDiff> fileDiffList = uploadFileService.queryFileDiff(0, 3);
        Assert.assertEquals(fileDiffList.size() > 0, true);
    }

    @Test
    public void testDeleteById() throws Exception {
        FileDiff fileDiff = new FileDiff();
        fileDiff.setSourceFile("a1=1");
        fileDiff.setTargetFile("a2=1");
        fileDiff.setFileDiff("-a1=1;<br>+a2=1<br>");
        int inserts = uploadFileService.insert(fileDiff);
        int deletes = uploadFileService.deleteById(1);
        Assert.assertEquals(deletes, 1);
    }
}