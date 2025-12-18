package com.example.trendyolgobaseapi.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trendyolgobaseapi.entities.classses.ListUI;

public interface ListUIRepository extends  JpaRepository<ListUI, Integer> {
    
}
