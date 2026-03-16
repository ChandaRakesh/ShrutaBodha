package com.shrutabodha.backend.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {
    public static final String VIDEO_QUEUE="video_processing_queue";

    @Bean
    public Queue videoQueue(){
        return new Queue(VIDEO_QUEUE,true);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        admin.setAutoStartup(true);
        return admin;
    }

    @Bean
    public CommandLineRunner declareQueues(RabbitAdmin rabbitAdmin, Queue videoQueue) {
        return args -> {
            rabbitAdmin.declareQueue(videoQueue);
            System.out.println("Queue declared: " + VIDEO_QUEUE);
        };
    }
}
