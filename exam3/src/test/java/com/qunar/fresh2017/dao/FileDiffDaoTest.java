package com.qunar.fresh2017.dao;

import com.qunar.fresh2017.model.FileDiff;
import junit.framework.Assert;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml", "classpath:/mvc-dispatcher-servlet.xml" })
public class FileDiffDaoTest {

    @Resource
    private FileDiffDao fileDiffDao;

    @Test
    public void testSaveFileDiff() throws Exception {
        FileDiff fileDiff = new FileDiff();
        fileDiff.setSourceFile("a1=1");
        fileDiff.setTargetFile("a2=1");
        fileDiff.setFileDiff("-a1=1;<br>+a2=1<br>");
        int inserts = fileDiffDao.saveFileDiff(fileDiff);
        Assert.assertEquals(inserts, 1);
    }

    @Test
    public void testQuerySum() throws Exception {
        int sum = fileDiffDao.querySum();
        Assert.assertEquals(sum >= 0, true);
    }

    @Test
    public void testQueryFileDiff() throws Exception {
        FileDiff fileDiff = new FileDiff();
        fileDiff.setSourceFile("a1=1");
        fileDiff.setTargetFile("a2=1");
        fileDiff.setFileDiff("-a1=1;<br>+a2=1<br>");
        fileDiffDao.saveFileDiff(fileDiff);
        List<FileDiff> fileDiffList = fileDiffDao.queryFileDiff(new RowBounds(0, 3));
        Assert.assertEquals(fileDiffList.size() > 0, true);
    }

    @Test
    public void testDeleteById() throws Exception {
        FileDiff fileDiff = new FileDiff();
        fileDiff.setSourceFile("a1=1");
        fileDiff.setTargetFile("a2=1");
        fileDiff.setFileDiff("-a1=1;<br>+a2=1<br>");
        fileDiffDao.saveFileDiff(fileDiff);
        fileDiffDao.saveFileDiff(fileDiff);
        int deletes = fileDiffDao.deleteById(2);
        Assert.assertEquals(deletes, 1);
    }
}