package com.example.trendyolgobaseapi.core.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageAndTitleDTO {
      
    private Integer id;

   
    private String url;

   
    private String title;
}
