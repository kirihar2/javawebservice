package com.example.demo.Controller;

import com.example.demo.Service.Service;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class Controller {
    @Autowired
    private Service service;

    @PostMapping(path="/upload")
    @ApiOperation(value = "upload a file and convert to tiff")
    public String post(@ApiParam("Select File for upload")
                     @RequestParam("file")MultipartFile file) throws IOException, InterruptedException {
            service.uploadFile(file);
            return "Success";
        }
}
