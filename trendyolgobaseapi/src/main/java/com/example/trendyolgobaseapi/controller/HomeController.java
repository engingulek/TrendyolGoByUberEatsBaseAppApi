package com.example.trendyolgobaseapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.trendyolgobaseapi.business.abstracts.HomeService;
import com.example.trendyolgobaseapi.core.response.ListUIDTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("api/trendyolGoBase")
@AllArgsConstructor
@NoArgsConstructor
public class HomeController {
     @Autowired
    private HomeService homeService;

    @GetMapping("/homeList")
    public List<ListUIDTO>  getList() {
        return   homeService.getData();
    }

    @GetMapping("/allList")
    public ListUIDTO getAllList(@RequestParam Integer id){
        return homeService.getAllListData(id);
    }
}
