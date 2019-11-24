package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@SuppressWarnings("MVCPathVariableInspection")
@Api(description = "主接口类")
@RestController
@RequestMapping(value = "/api/")
public class MainController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${uploadFilePath}")
    private String uploadFilePath;

    @ApiOperation(value = "GET方法测试", notes = "测试GET方法")
    @GetMapping("test")
    Result testGet() {
        log.info("test get ...");
        return Result.ok();
    }

    @ApiOperation(value = "GET方法测试", notes = "测试GET方法传参")
    @GetMapping("test/{id}")
    Result testGet(@PathVariable("id") Integer id) {
        log.info("test get ... " + id);
        return Result.ok();
    }

    @ApiOperation(value = "POST方法测试", notes = "测试POST方法")
    @PostMapping("test/{id}")
    Result testPost(@PathVariable(value = "id") Integer id) {
        log.info("test post ... " + id);
        return Result.ok();
    }

    @ApiOperation(value = "PUT方法测试", notes = "测试PUT方法")
    @PutMapping("test/{id}")
    Result testPut(@PathVariable(value = "id") Integer id) {
        log.info("test put ... " + id);
        return Result.ok();
    }

    @ApiOperation(value = "DELETE方法测试", notes = "测试DELETE方法")
    @DeleteMapping("test/{id}")
    Result testDelete(@PathVariable(value = "id") Integer id) {
        log.info("test delete ... " + id);
        return Result.ok();
    }

    @ApiOperation(value = "POST方法测试", notes = "测试POST方法")
    @PostMapping("test")
    Result testPost(@RequestBody JSONObject params) {
        log.info("test post ... " + params.toJSONString());
        return Result.ok();
    }

    @ApiOperation(value = "PUT方法测试", notes = "测试PUT方法")
    @PutMapping("test")
    Result testPut(@RequestBody JSONObject params) {
        log.info("test put ... " + params.toJSONString());
        return Result.ok();
    }

    @ApiOperation(value = "文件上传", notes = "单文件上传")
    @PostMapping("upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return Result.build(400, "文件为空");
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = null;
            if (fileName != null) {
                suffixName = fileName.substring(fileName.lastIndexOf("."));
            }
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
//            String filePath = "D:\\test\\";
            String path = uploadFilePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                boolean mkdirs = dest.getParentFile().mkdirs();// 新建文件夹
                log.info("创建目录结果：" + mkdirs);
            }
            file.transferTo(dest);// 文件写入
            return Result.build(200, "上传成功");
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return Result.build(500, "上传失败");
    }

    @ApiOperation(value = "文件上传", notes = "多文件上传")
    @PostMapping("batch")
    public Result handleFileUpload(@RequestParam("file") MultipartFile[] files) throws IOException {
        MultipartFile file;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.length; ++i) {
            file = files[i];
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(uploadFilePath + file.getOriginalFilename())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    if (stream != null) {
                        stream.close();
                    }
                    return Result.build(500, "第 " + i + " 个文件上传失败 ==> "
                            + e.getMessage());
                }
            } else {
                return Result.build(400, "第 " + i
                        + " 个文件上传失败因为文件为空");
            }
        }
        return Result.build(200, "上传成功");
    }

    @ApiOperation(value = "文件下载", notes = "单文件下载")
    @GetMapping("download")
    public Result downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "headshot.jpg";// 文件名
        //设置文件路径
        File file = new File(uploadFilePath, fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return Result.build(200, "下载成功");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return Result.build(500, "下载失败");
    }

}
