package com.example.trendyolgobaseapi.core.response;

import java.util.List;

import com.example.trendyolgobaseapi.entities.enums.DeliveryType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantInfoDTO {
     private Integer id;

    private String imageUrl;

    private Double rating;

    private Integer reviewCount;

    private String name;

    private Double minimumPrice;

    private Double distanceKm;

   
  
    private String category;

    private Integer deliveryTime;

    
    private DeliveryType deliveryType;


  
    private List<String> campaignList;
}
