package com.shrutabodha.backend.service;

import com.shrutabodha.backend.dto.VideoProcessingMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class VideoProcessingProducerService {
    private final RabbitTemplate rabbitTemplate;

    private static final String QUEUE_NAME="video_processing_queue";

    public VideoProcessingProducerService(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;
    }

    public void sendVideoForProcessing(VideoProcessingMessage message){
        rabbitTemplate.convertAndSend(QUEUE_NAME,message);
    }
}
