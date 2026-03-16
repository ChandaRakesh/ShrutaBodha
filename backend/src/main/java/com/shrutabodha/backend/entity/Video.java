package com.shrutabodha.backend.entity;

import com.shrutabodha.backend.enums.VideoStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;//why do we map User instead of userId?->Because ORM maps object relationships, not raw foreign keys.

    private String name;

    @Column(name = "s3_url")
    private String s3Url;

    //enum will be stored in the form or ordinal instead of string
    //once check this notes in notion
    @Enumerated(EnumType.STRING)
    private VideoStatus status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}
