package com.appdeploy.fastdeploy.image.repository;

import com.appdeploy.fastdeploy.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {

    @Query(value = "select * from image where app_id=?1 AND image_type=?2",nativeQuery = true)
    Optional<List<Image>> findImageByAppId(Long id,String imageType);

    @Query(value = "select * from image where app_id=?1 AND image_type=?2",nativeQuery = true)
    Optional<Image> findIconByApp(Long id,String imageType);
}
