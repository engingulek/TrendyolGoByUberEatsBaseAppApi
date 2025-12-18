package com.example.trendyolgobaseapi.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trendyolgobaseapi.entities.classses.DataInfo;

public interface  DataInfoRepository extends  JpaRepository<DataInfo, Integer> {
    
}
