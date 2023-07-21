package com.example.demo.user;

import com.example.demo.event.listener.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/cvs",consumes = MediaType.ALL_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class CvController {
    @Autowired
    private CvService service;
    private static final Logger logger = LoggerFactory.getLogger(CvController.class);



    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = service.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }



    @DeleteMapping("/deleteCv/{id}")
    public void deletecv(@PathVariable("id") Long id){
        service.deleteCv(id);
    }
}
