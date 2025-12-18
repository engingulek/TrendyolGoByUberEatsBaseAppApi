package com.example.trendyolgobaseapi.core.response;

import java.util.List;
import java.util.Optional;

import com.example.trendyolgobaseapi.entities.enums.CellType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListUIDTO<T> {

    private Integer id;
    private String title;
    private Optional<HeaderImageDTO> headerImageDTO;
    private LayoutTemplateDTO layoutTemplateDTO;

    private CellType cellType;
    private List<String> buttonTypes;
    private List<T> data;
}

