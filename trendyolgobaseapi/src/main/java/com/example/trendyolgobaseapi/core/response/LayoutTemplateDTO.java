package com.example.trendyolgobaseapi.core.response;
import com.example.trendyolgobaseapi.entities.enums.GroupOrientation;
import com.example.trendyolgobaseapi.entities.enums.LayoutTemplate;
import com.example.trendyolgobaseapi.entities.enums.ScrollDirection;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LayoutTemplateDTO {
    @Enumerated(EnumType.STRING)
  private LayoutTemplate layoutTemplate;

   @Enumerated(EnumType.STRING)
  private ScrollDirection scrollDirection;

  @Enumerated(EnumType.STRING)
  private GroupOrientation groupOrientation;
}
