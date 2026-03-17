package com.shrutabodha.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;

import java.net.URI;

@Configuration
public class S3Config {
    @Bean
    public S3Client s3Client(){
        return S3Client.builder()
                .endpointOverride(URI.create("http://localhost:4566"))
                .forcePathStyle(true)
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                              AwsBasicCredentials.create("test","test")
                        )
                )
                .region(Region.US_EAST_1)
                .build();
    }
    @Bean
    public CommandLineRunner createBucket(S3Client s3Client){
        return args ->{
            String bucketName = "shrutabodha-videos";
            
            try{
                //check if bucket exists.
                s3Client.headBucket(
                    HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build()
                );
                System.out.println("Bucket already exists:"+bucketName);
            }catch(Exception e){
                s3Client.createBucket(
                    CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build()
                );
                System.out.println("Bucket created: " + bucketName);
            }
        };
    }
    
}
