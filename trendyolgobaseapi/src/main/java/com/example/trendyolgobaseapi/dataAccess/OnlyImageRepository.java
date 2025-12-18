package com.example.trendyolgobaseapi.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trendyolgobaseapi.entities.classses.OnlyImage;

public interface OnlyImageRepository extends  JpaRepository<OnlyImage, Integer> {
    List<OnlyImage> findByDataInfoId(Integer dataInfoId);
}
