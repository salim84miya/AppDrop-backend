package com.appdeploy.fastdeploy.security.entity;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class RefreshToken {

    private Long id;

    private String token;

    private String username;

    private Date expirationTime;
}
