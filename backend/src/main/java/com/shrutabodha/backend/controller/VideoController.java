package com.shrutabodha.backend.controller;

import com.shrutabodha.backend.entity.Video;
import com.shrutabodha.backend.service.VideoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService){
        this.videoService=videoService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Video uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam UUID userId,
            @RequestParam String name
    ) throws Exception{
        return videoService.uploadVideo(file,userId,name);
    }
}
