package com.example.trendyolgobaseapi.entities.classses;
import java.util.List;

import com.example.trendyolgobaseapi.entities.enums.DeliveryType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Entity
@Table(name = "restaurant_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imageUrl;
    private Double rating;
    private Integer reviewCount;
    private String name;
    private Double minimumPrice;
    private Double distanceKm;

    private Integer deliveryTime;

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @Column(name = "campaign_list")
    private List<String> campaignList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_info_id")
    private DataInfo dataInfo;
}
