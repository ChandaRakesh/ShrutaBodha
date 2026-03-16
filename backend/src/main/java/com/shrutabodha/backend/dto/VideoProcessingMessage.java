package com.shrutabodha.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class VideoProcessingMessage {
    private UUID videoId;
    private String s3Url;
}
