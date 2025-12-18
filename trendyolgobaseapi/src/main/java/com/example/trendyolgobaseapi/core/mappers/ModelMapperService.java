package com.example.trendyolgobaseapi.core.mappers;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
ModelMapper forResponse();
    ModelMapper forRequest();
}

