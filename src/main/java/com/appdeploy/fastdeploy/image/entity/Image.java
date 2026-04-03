package com.appdeploy.fastdeploy.image.entity;

import com.appdeploy.fastdeploy.image.dto.ImageType;
import com.appdeploy.fastdeploy.project.entity.App;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "image",indexes = {@Index(name="publicIdIndex",columnList = "publicId")})
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String url;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    @Column(unique = true)
    private String publicId;

    @ManyToOne
    @JoinColumn
    private App app;

}
