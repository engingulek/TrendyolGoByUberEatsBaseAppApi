package com.example.trendyolgobaseapi.business.contracts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trendyolgobaseapi.business.abstracts.HomeService;
import com.example.trendyolgobaseapi.core.response.HeaderImageDTO;
import com.example.trendyolgobaseapi.core.response.ImageAndTitleDTO;
import com.example.trendyolgobaseapi.core.response.LayoutTemplateDTO;
import com.example.trendyolgobaseapi.core.response.ListUIDTO;
import com.example.trendyolgobaseapi.core.response.OnlyImageDTO;
import com.example.trendyolgobaseapi.core.response.RestaurantInfoDTO;
import com.example.trendyolgobaseapi.dataAccess.DataInfoRepository;
import com.example.trendyolgobaseapi.dataAccess.ImageAndTitleRepository;
import com.example.trendyolgobaseapi.dataAccess.ListUIRepository;
import com.example.trendyolgobaseapi.dataAccess.OnlyImageRepository;
import com.example.trendyolgobaseapi.dataAccess.RestaurantInfoRepository;
import com.example.trendyolgobaseapi.entities.classses.DataInfo;
import com.example.trendyolgobaseapi.entities.classses.ListUI;
import com.example.trendyolgobaseapi.entities.enums.CellType;
import com.example.trendyolgobaseapi.entities.enums.DataType;
import com.example.trendyolgobaseapi.entities.enums.GroupOrientation;
import com.example.trendyolgobaseapi.entities.enums.LayoutTemplate;
import com.example.trendyolgobaseapi.entities.enums.ScrollDirection;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class HomeManager implements HomeService {

    @Autowired
    private ListUIRepository listUIRepository;
    @Autowired
    private OnlyImageRepository onlyImageRepository;
    @Autowired
    private RestaurantInfoRepository restaurantInfoRepository;
    @Autowired
    private ImageAndTitleRepository imageAndTitleRepository;
    @Autowired
    private DataInfoRepository dataInfoRepository;

    private ListUIDTO<?> restauransListUIDTO;

    @Override
    public List getData() {

        List<ListUI> listUIs = listUIRepository.findAll();
        List<ListUIDTO<?>> listUIDTOs = new ArrayList<>();

        for (ListUI item : listUIs) {

            DataInfo dataInfo = dataInfoRepository.findById(
                    item.getDataInfo().getId()
            ).orElseThrow(() -> new RuntimeException("Match not found"));

            switch (dataInfo.getDataType()) {

                case only_image -> {
                    List<OnlyImageDTO> dtos = onlyImageRepository
                            .findByDataInfoId(dataInfo.getId())
                            .stream()
                            .map(d -> new OnlyImageDTO(d.getId(), d.getUrl()))
                            .toList();

                    listUIDTOs.add(createListUIDTO(item, dataInfo, dtos));
                }

                case restaurant_info -> {
                    List<RestaurantInfoDTO> dtos = restaurantInfoRepository
                            .findByDataInfoId(dataInfo.getId())
                            .stream()
                            .map(d -> new RestaurantInfoDTO(
                                    d.getId(),
                                    d.getImageUrl(),
                                    d.getRating(),
                                    d.getReviewCount(),
                                    d.getName(),
                                    d.getMinimumPrice(),
                                    d.getDistanceKm(),
                                    d.getCategory().getName(),
                                    d.getDeliveryTime(),
                                    d.getDeliveryType(),
                                    d.getCampaignList()
                            ))
                            .toList();

                    if (dataInfo.getId() == 4) {
                        restauransListUIDTO = createListUIDTO(item, dataInfo, dtos);
                    } else {
                        listUIDTOs.add(createListUIDTO(item, dataInfo, dtos));
                    }
                }

                case image_title -> {
                    List<ImageAndTitleDTO> dtos = imageAndTitleRepository
                            .findByDataInfoId(dataInfo.getId())
                            .stream()
                            .map(d -> new ImageAndTitleDTO(
                                    d.getId(),
                                    d.getImageUrl(),
                                    d.getTitle()
                            ))
                            .toList();

                    listUIDTOs.add(createListUIDTO(item, dataInfo, dtos));
                }
            }
        }

        if (restauransListUIDTO != null) {
            listUIDTOs.add(restauransListUIDTO);
        }

        return listUIDTOs;
    }

    /* ------------------------------------------------------ */

    private <T> ListUIDTO<T> createListUIDTO(
            ListUI item,
            DataInfo dataInfo,
            List<T> dtos
    ) {
        return new ListUIDTO<>(
                item.getId(),
                dataInfo.getTitle(),
                Optional.ofNullable(
                        dataInfo.getImage() == null ? null :
                                new HeaderImageDTO(
                                        dataInfo.getImage().getId(),
                                        dataInfo.getImage().getType(),
                                        dataInfo.getImage().getImageText(),
                                        dataInfo.getImage().getColor()
                                )
                ),
                new LayoutTemplateDTO(
                        item.getLayoutTemplate(),
                        item.getScrollDirection(),
                        item.getGroupOrientation()
                ),
                getCellType(dataInfo.getDataType(), item.getLayoutTemplate()),
                item.getButtontypes(),
                dtos
        );
    }

    /* ------------------------------------------------------ */

    private <T> ListUIDTO<T> createAllListUIDTO(
            ListUI item,
            DataInfo dataInfo,
            List<T> dtos
    ) {

        Optional<HeaderImageDTO> headerImage =
                Optional.ofNullable(
                        dataInfo.getImage() == null ? null :
                                new HeaderImageDTO(
                                        dataInfo.getImage().getId(),
                                        dataInfo.getImage().getType(),
                                        dataInfo.getImage().getImageText(),
                                        dataInfo.getImage().getColor()
                                )
                );

        return switch (dataInfo.getDataType()) {

            case restaurant_info -> new ListUIDTO<>(
                    item.getId(),
                    dataInfo.getTitle(),
                    headerImage,
                    new LayoutTemplateDTO(
                            LayoutTemplate.featured_01,
                            ScrollDirection.vertical,
                            GroupOrientation.horizontal
                    ),
                    CellType.featured_01_info,
                    Arrays.asList("View"),
                    dtos
            );

            default -> new ListUIDTO<>(
                    item.getId(),
                    dataInfo.getTitle(),
                    headerImage,
                    new LayoutTemplateDTO(
                            item.getLayoutTemplate(),
                            ScrollDirection.vertical,
                            GroupOrientation.horizontal
                    ),
                    getCellType(dataInfo.getDataType(), item.getLayoutTemplate()),
                    item.getButtontypes(),
                    dtos
            );
        };
    }

    /* ------------------------------------------------------ */

    private CellType getCellType(DataType dataType, LayoutTemplate layoutTemplate) {
        return switch (dataType) {
            case image_title -> CellType.card_image_title;
            case only_image -> CellType.card_image;
            case restaurant_info -> {
                if (layoutTemplate == LayoutTemplate.featured_01) {
                    yield CellType.featured_01_info;
                } else if (layoutTemplate == LayoutTemplate.featured_02) {
                    yield CellType.featured_02_info;
                }
                yield CellType.card_info;
            }
        };
    }

    /* ------------------------------------------------------ */

    @Override
    public ListUIDTO<?> getAllListData(Integer id) {

        ListUI listUI = listUIRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UI not found"));

        DataInfo dataInfo = dataInfoRepository.findById(
                        listUI.getDataInfo().getId()
                )
                .orElseThrow(() -> new RuntimeException("DataInfo not found"));

        return switch (dataInfo.getDataType()) {

            case image_title -> {
                List<ImageAndTitleDTO> dtos = imageAndTitleRepository
                        .findByDataInfoId(dataInfo.getId())
                        .stream()
                        .map(d -> new ImageAndTitleDTO(
                                d.getId(),
                                d.getImageUrl(),
                                d.getTitle()
                        ))
                        .toList();

                yield createAllListUIDTO(listUI, dataInfo, dtos);
            }

            case only_image -> {
                List<OnlyImageDTO> dtos = onlyImageRepository
                        .findByDataInfoId(dataInfo.getId())
                        .stream()
                        .map(d -> new OnlyImageDTO(
                                d.getId(),
                                d.getUrl()
                        ))
                        .toList();

                yield createAllListUIDTO(listUI, dataInfo, dtos);
            }

            case restaurant_info -> {
                List<RestaurantInfoDTO> dtos = restaurantInfoRepository
                        .findByDataInfoId(dataInfo.getId())
                        .stream()
                        .map(d -> new RestaurantInfoDTO(
                                d.getId(),
                                d.getImageUrl(),
                                d.getRating(),
                                d.getReviewCount(),
                                d.getName(),
                                d.getMinimumPrice(),
                                d.getDistanceKm(),
                                d.getCategory().getName(),
                                d.getDeliveryTime(),
                                d.getDeliveryType(),
                                d.getCampaignList()
                        ))
                        .toList();

                yield createAllListUIDTO(listUI, dataInfo, dtos);
            }
        };
    }
}
