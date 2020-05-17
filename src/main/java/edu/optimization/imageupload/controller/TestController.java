package edu.optimization.imageupload.controller;

import edu.optimization.imageupload.entity.Image;
import edu.optimization.imageupload.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class TestController {


    @Autowired
    ImageService imageService;


    @RequestMapping(value = "/uploadImage")
    public String addImage(@RequestParam("title") String title,
                           @RequestParam("image") MultipartFile image)
            throws IOException {
        String id = imageService.addImage(title, image);
        return id;
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String id) {
        Image image = imageService.getPhoto(id);
       /* model.addAttribute("title", image.getTitle());
        model.addAttribute("image", Base64.getEncoder().encodeToString(image.getImage().getData()));*/


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getTitle() + "\"")
                .body(new ByteArrayResource(image.getImage().getData()));
    }


}
