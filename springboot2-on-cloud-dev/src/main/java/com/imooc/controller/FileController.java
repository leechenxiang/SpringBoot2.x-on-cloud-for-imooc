package com.imooc.controller;

import com.imooc.MinIOConfig;
import com.imooc.utils.MinIOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileController {

    @GetMapping("fileUploader")
    public String fileUploader(){
        return "fileUploader";
    }

    @Autowired
    private MinIOConfig minIOConfig;

    @PostMapping("upload")
    public String upload(MultipartFile file,
                         Model model,
                         HttpServletRequest request) throws Exception {

        String fileName = file.getOriginalFilename();

        MinIOUtils.uploadFile(
                minIOConfig.getBucketName(),
                fileName,
                file.getInputStream());

        String imgUrl = minIOConfig.getFileHost()
                + "/"
                + minIOConfig.getBucketName()
                + "/"
                + fileName;

        model.addAttribute("imgUrl", imgUrl);
        return "fileUploader";
    }

}
