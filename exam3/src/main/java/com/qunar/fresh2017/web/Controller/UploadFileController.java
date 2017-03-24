package com.qunar.fresh2017.web.Controller;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.qunar.fresh2017.model.FileDiff;
import com.qunar.fresh2017.service.UploadFileService;
import com.qunar.fresh2017.service.impl.UploadFileServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by kingsley.zhang on 2017/3/23.
 * 控制层，上传文件功能方面的一系列控制器
 */
@Controller
public class UploadFileController {
    private static final String FILEPATH = UploadFileServiceImpl.class.getResource("/").getPath(); // 文件存放目录
    private String sourcePath; // 源文件存放地址
    private String targetPath; // 目标文件存放地址
    @Resource
    private UploadFileService uploadFileService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileController.class);

    /*
     * 显示文件上传页面
     */
    @RequestMapping(value = "/uploadFile.do")
    @ResponseBody
    public ModelAndView uploadFile(Integer startId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        // 提取数据库数据：历史文件差别记录的总数，以及最近的2条记录
        int sum = uploadFileService.querySum();
        modelAndView.addObject("sum", sum); // 传入记录结果总数
        modelAndView.addObject("start", startId); // 传入下一次分页时的起始位置
        List<FileDiff> fileDiffList = uploadFileService.queryFileDiff(startId, 3);
        modelAndView.addObject("fileDiffList", fileDiffList);

        // 检查用户信息，如果用户登录了，将用户名传入页面
        String userName = (String) request.getSession().getAttribute("userName");
        if (!Strings.isNullOrEmpty(userName)) {
            modelAndView.addObject("userName", userName);
        }
        modelAndView.setViewName("uploadFile");
        return modelAndView;
    }

    /*
     * 上传文件，比较文件并将差异存入数据库
     */
    @RequestMapping("/processingFile.do")
    @ResponseBody
    public ModelAndView upload(MultipartFile source, MultipartFile target) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Preconditions.checkNotNull(source, "没有上传源文件");
            Preconditions.checkNotNull(target, "没有上传目标文件");
            sourcePath = FILEPATH + source.getOriginalFilename();
            targetPath = FILEPATH + target.getOriginalFilename();
            uploadFileService.uploadFiles(sourcePath, targetPath);  //只传递String过去，框架非侵入性，保证service的东西不涉及srping框架
            source.transferTo(new File(sourcePath));    // 上传文件
            target.transferTo(new File(targetPath));

        } catch (IOException e) {
            LOGGER.error("上传文件异常！", e);
            modelAndView.addObject("message", "上传文件异常！");
            modelAndView.setViewName("error");
            return modelAndView;
        }
        try {
            uploadFileService.compareAndSaveFileDiff(); // 比较文件差异，将结果存入数据库
        } catch (IOException e) {
            LOGGER.error("文件比较和保存操作发生异常！", e);
            modelAndView.addObject("message", "文件比较和保存操作发生异常！");
            modelAndView.setViewName("error");
            return modelAndView;
        }
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /*
     * 删除指定的历史文件差异记录
     */
    @RequestMapping("/deleteFileDiff.do")
    @ResponseBody
    public ModelAndView deleteFileDiff(Integer fileDiffId) {
        ModelAndView modelAndView = new ModelAndView();
        int deletes = uploadFileService.deleteById(fileDiffId);
        if (deletes > 0) {
            modelAndView.setViewName("success");
            return modelAndView;
        }
        modelAndView.addObject("message", "删除失败！");
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
