package com.shrutabodha.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;//Lombok reduces boilerplate code by automatically generating getters,setters, constructors and builders during compilation.

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor //why this is needed?//Frameworks like hibernate and jpa require constructor to create objects using reflection
@AllArgsConstructor// to quickly create objects
@Builder//helps to create builder designer pattern. like User user=User.builder().id(1).name("rakesh").....etc.// cleaner object creation, avoids large constructors, good when entity has many fields.
public class User{
    @Id
    private UUID id;

    @Column(nullable=false,unique=true)
    private String email;

    private String name;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="created_at")
    private Instant createdAt;

    @Column(name="updated_at")
    private Instant updatedAt;
}