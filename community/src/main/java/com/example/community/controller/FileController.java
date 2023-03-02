package com.example.community.controller;

import com.example.community.common.Result;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("manpower")
    public Result uploads(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response)throws IOException {
        //获取当前项目所在绝对路径
        String absolutePath=System.getProperty("user.dir");
        //文件上传后所在的绝对路径
        String savePath=absolutePath+"/src/main/resources/static/fileupload/manpower/";
        //保存在数据库的文件地址
        String saveUrl="/fileupload/manpower/";
        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");

        // 最大文件大小
        long maxSize = 10000000; //10M
        response.setContentType("text/html; charset=UTF-8");
        if (!ServletFileUpload.isMultipartContent(request)) {

            return new Result(false, 999,"最大文件大小");
        }
        File uploadDir = new File(savePath);
        // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 检查目录写权限
        if (!uploadDir.canWrite()) {
            return new Result(false, 999,"检查目录写权限");
        }

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if (!extMap.containsKey(dirName)) {
            return new Result(false, 999,"检查目录写权限");
        }
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        String fileName = null;
        Iterator<Map.Entry<String, MultipartFile>> iter = fileMap.entrySet().iterator();
        for (String s : fileMap.keySet()) {

        }
        for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();
            fileName = mFile.getOriginalFilename();
            // 检查文件大小
            if (mFile.getSize() > maxSize) {
                return new Result(false, 999,"最大文件大小");
            }
            String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
            if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                return new Result(false, 999,"最大文件大小");
            }
            UUID uuid = UUID.randomUUID();
            String path = savePath + uuid.toString() +"."+ fileExt;
            System.out.println("文件存储地址:【"+path+"】");
            saveUrl = saveUrl  + uuid.toString() +"."+ fileExt;

            try {
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path));
                FileCopyUtils.copy(mFile.getInputStream(), outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Result(true,200,"文件上传成功！",saveUrl);
    }
    @GetMapping(value="/delmanpower")
    public Result fileDel(@RequestParam String fileName){
        String name = fileName.replace("/fileupload/manpower/","");
        //获取当前项目所在绝对路径
        String absolutePath = System.getProperty("user.dir");
        //文件上传后所在绝对路径
        String savePath = absolutePath+"/src/main/resources/static/fileupload/manpower/";
        File file = new File(savePath+name);
        if (file.exists()){//文件是否存在
            file.delete();//删除文件
        }
        return new Result(true,200, "文件删除成功！");
    }


    @PostMapping("manager")
    public Result uploads1(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response)throws IOException {
        //获取当前项目所在绝对路径
        String absolutePath=System.getProperty("user.dir");
        //文件上传后所在的绝对路径
        String savePath=absolutePath+"/src/main/resources/static/fileupload/manager/";
        //保存在数据库的文件地址
        String saveUrl="/fileupload/manager/";
        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");

        // 最大文件大小
        long maxSize = 10000000; //10M
        response.setContentType("text/html; charset=UTF-8");
        if (!ServletFileUpload.isMultipartContent(request)) {

            return new Result(false, 999,"最大文件大小");
        }
        File uploadDir = new File(savePath);
        // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 检查目录写权限
        if (!uploadDir.canWrite()) {
            return new Result(false, 999,"检查目录写权限");
        }

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if (!extMap.containsKey(dirName)) {
            return new Result(false, 999,"检查目录写权限");
        }
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        String fileName = null;
        Iterator<Map.Entry<String, MultipartFile>> iter = fileMap.entrySet().iterator();
        for (String s : fileMap.keySet()) {

        }
        for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();
            fileName = mFile.getOriginalFilename();
            // 检查文件大小
            if (mFile.getSize() > maxSize) {
                return new Result(false, 999,"最大文件大小");
            }
            String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
            if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                return new Result(false, 999,"最大文件大小");
            }
            UUID uuid = UUID.randomUUID();
            String path = savePath + uuid.toString() +"."+ fileExt;
            System.out.println("文件存储地址:【"+path+"】");
            saveUrl = saveUrl  + uuid.toString() +"."+ fileExt;

            try {
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path));
                FileCopyUtils.copy(mFile.getInputStream(), outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Result(true,200,"文件上传成功！",saveUrl);
    }
    @GetMapping(value="/delmanager")
    public Result fileDel1(@RequestParam String fileName){
        String name = fileName.replace("/fileupload/manager/","");
        //获取当前项目所在绝对路径
        String absolutePath = System.getProperty("user.dir");
        //文件上传后所在绝对路径
        String savePath = absolutePath+"/src/main/resources/static/fileupload/manager/";
        File file = new File(savePath+name);
        if (file.exists()){//文件是否存在
            file.delete();//删除文件
        }
        return new Result(true,200, "文件删除成功！");
    }

    @PostMapping("car")
    public Result uploads2(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response)throws IOException {
        //获取当前项目所在绝对路径
        String absolutePath=System.getProperty("user.dir");
        //文件上传后所在的绝对路径
        String savePath=absolutePath+"/src/main/resources/static/fileupload/car/";
        //保存在数据库的文件地址
        String saveUrl="/fileupload/car/";
        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");

        // 最大文件大小
        long maxSize = 10000000; //10M
        response.setContentType("text/html; charset=UTF-8");
        if (!ServletFileUpload.isMultipartContent(request)) {

            return new Result(false, 999,"最大文件大小");
        }
        File uploadDir = new File(savePath);
        // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 检查目录写权限
        if (!uploadDir.canWrite()) {
            return new Result(false, 999,"检查目录写权限");
        }

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if (!extMap.containsKey(dirName)) {
            return new Result(false, 999,"检查目录写权限");
        }
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        String fileName = null;
        Iterator<Map.Entry<String, MultipartFile>> iter = fileMap.entrySet().iterator();
        for (String s : fileMap.keySet()) {

        }
        for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();
            fileName = mFile.getOriginalFilename();
            // 检查文件大小
            if (mFile.getSize() > maxSize) {
                return new Result(false, 999,"最大文件大小");
            }
            String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
            if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                return new Result(false, 999,"最大文件大小");
            }
            UUID uuid = UUID.randomUUID();
            String path = savePath + uuid.toString() +"."+ fileExt;
            System.out.println("文件存储地址:【"+path+"】");
            saveUrl = saveUrl  + uuid.toString() +"."+ fileExt;

            try {
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path));
                FileCopyUtils.copy(mFile.getInputStream(), outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Result(true,200,"文件上传成功！",saveUrl);
    }
    @GetMapping(value="/delcar")
    public Result fileDel2(@RequestParam String fileName){
        String name = fileName.replace("/fileupload/car/","");
        //获取当前项目所在绝对路径
        String absolutePath = System.getProperty("user.dir");
        //文件上传后所在绝对路径
        String savePath = absolutePath+"/src/main/resources/static/fileupload/car/";
        File file = new File(savePath+name);
        if (file.exists()){//文件是否存在
            file.delete();//删除文件
        }
        return new Result(true,200, "文件删除成功！");
    }

    @PostMapping("pet")
    public Result uploads3(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response)throws IOException {
        //获取当前项目所在绝对路径
        String absolutePath=System.getProperty("user.dir");
        //文件上传后所在的绝对路径
        String savePath=absolutePath+"/src/main/resources/static/fileupload/pet/";
        //保存在数据库的文件地址
        String saveUrl="/fileupload/pet/";
        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");

        // 最大文件大小
        long maxSize = 10000000; //10M
        response.setContentType("text/html; charset=UTF-8");
        if (!ServletFileUpload.isMultipartContent(request)) {

            return new Result(false, 999,"最大文件大小");
        }
        File uploadDir = new File(savePath);
        // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 检查目录写权限
        if (!uploadDir.canWrite()) {
            return new Result(false, 999,"检查目录写权限");
        }

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if (!extMap.containsKey(dirName)) {
            return new Result(false, 999,"检查目录写权限");
        }
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        String fileName = null;
        Iterator<Map.Entry<String, MultipartFile>> iter = fileMap.entrySet().iterator();
        for (String s : fileMap.keySet()) {

        }
        for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();
            fileName = mFile.getOriginalFilename();
            // 检查文件大小
            if (mFile.getSize() > maxSize) {
                return new Result(false, 999,"最大文件大小");
            }
            String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
            if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                return new Result(false, 999,"最大文件大小");
            }
            UUID uuid = UUID.randomUUID();
            String path = savePath + uuid.toString() +"."+ fileExt;
            System.out.println("文件存储地址:【"+path+"】");
            saveUrl = saveUrl  + uuid.toString() +"."+ fileExt;

            try {
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path));
                FileCopyUtils.copy(mFile.getInputStream(), outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Result(true,200,"文件上传成功！",saveUrl);
    }
    @GetMapping(value="/delpet")
    public Result fileDel3(@RequestParam String fileName){
        String name = fileName.replace("/fileupload/pet/","");
        //获取当前项目所在绝对路径
        String absolutePath = System.getProperty("user.dir");
        //文件上传后所在绝对路径
        String savePath = absolutePath+"/src/main/resources/static/fileupload/pet/";
        File file = new File(savePath+name);
        if (file.exists()){//文件是否存在
            file.delete();//删除文件
        }
        return new Result(true,200, "文件删除成功！");
    }
}
