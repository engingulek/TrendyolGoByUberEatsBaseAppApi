package com.example.trendyolgobaseapi.entities.classses;

import com.example.trendyolgobaseapi.entities.enums.ImageType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "header_images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeaderImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false) 
    private ImageType type;

    @Column(name = "image_text")
    private String imageText;

    @Column
    private String color;
}

