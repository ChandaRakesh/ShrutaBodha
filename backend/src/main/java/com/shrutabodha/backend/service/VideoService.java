package com.shrutabodha.backend.service;

//responsibilities
//recieve Multipartfile
//gererate video id
//build s3 object key
//upload file to s3
//save metadata in database

import com.shrutabodha.backend.entity.User;
import com.shrutabodha.backend.entity.Video;
import com.shrutabodha.backend.enums.VideoStatus;
import com.shrutabodha.backend.repository.UserRepository;
import com.shrutabodha.backend.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.time.Instant;
import java.util.UUID;

@Service
public class VideoService {
    private final S3Client s3Client;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    private final String bucketName="shrutabodha-videos";


    public VideoService(S3Client s3Client,
                        VideoRepository videoRepository,
                        UserRepository userRepository){
        this.s3Client=s3Client;
        this.videoRepository=videoRepository;
        this.userRepository=userRepository;
    }

    public Video uploadVideo(MultipartFile file, UUID userId,String name) throws Exception{
        UUID videoId=UUID.randomUUID();
        User user=userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));

        String extension=file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));
        String key=userId+"/"+videoId+extension;
        PutObjectRequest request=PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.putObject(
                request,
                software.amazon.awssdk.core.sync.RequestBody.fromInputStream(
                        file.getInputStream(),
                        file.getSize()
                )
        );

        String fileUrl="http://localhost:4566/"+bucketName+"/"+key;

        Video video=Video.builder()
                .id(videoId)
                .user(user)
                .name(name)
                .s3Url(fileUrl)
                .status(VideoStatus.UPLOADED)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        return videoRepository.save(video);

    }


}
