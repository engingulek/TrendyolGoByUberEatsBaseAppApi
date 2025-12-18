package com.example.trendyolgobaseapi.core.response;

import com.example.trendyolgobaseapi.entities.enums.ImageType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeaderImageDTO {

    private Integer id;
    @Enumerated(EnumType.STRING)
    private ImageType type;
    private String imageText;
    private String color;
}
