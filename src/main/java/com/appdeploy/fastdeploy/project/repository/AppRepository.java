package com.appdeploy.fastdeploy.project.repository;

import com.appdeploy.fastdeploy.project.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<App,Long> {
}
