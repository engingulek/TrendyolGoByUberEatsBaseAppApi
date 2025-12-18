package com.example.trendyolgobaseapi.business.abstracts;

import java.util.List;

import com.example.trendyolgobaseapi.core.response.ListUIDTO;

public interface  HomeService {
     List<ListUIDTO> getData();
      ListUIDTO getAllListData(Integer id);
}
