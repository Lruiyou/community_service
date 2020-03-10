package com.alan.project.controller;

import com.alan.project.dto.FileDTO;
import com.alan.project.dto.Result;
import com.alan.project.enums.ResultCode;
import com.alan.project.service.UfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class FileUploadController {

    @Autowired
    private UfileService ufileService;

    @RequestMapping("/file/upload")
    @ResponseBody
    public Result upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            int width = image.getWidth();
            int height = image.getHeight();
            if (width >= 600 || height >= 600){
                return Result.failure(ResultCode.IMAGE_SIZE_ERROR);
            }else {
                String fileUrl = ufileService.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
            FileDTO fileDTO = new FileDTO();
            fileDTO.setName(file.getOriginalFilename());
            fileDTO.setUrl(fileUrl);
            return Result.success(fileDTO);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.failure(ResultCode.ERROR);
        }
//        try {
//            String fileUrl = ufileService.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
//            FileDTO fileDTO = new FileDTO();
//            fileDTO.setName(file.getOriginalFilename());
//            fileDTO.setUrl(fileUrl);
//            return Result.success(fileDTO);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return Result.failure(ResultCode.ERROR);
//        }
    }
}
