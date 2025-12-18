package com.example.trendyolgobaseapi.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trendyolgobaseapi.entities.classses.RestaurantInfo;

public interface  RestaurantInfoRepository extends JpaRepository<RestaurantInfo, Integer>  {
    List<RestaurantInfo> findByDataInfoId(Integer dataInfoId);
}
