package com.example.trendyolgobaseapi.entities.classses;
import java.util.List;

import com.example.trendyolgobaseapi.entities.enums.GroupOrientation;
import com.example.trendyolgobaseapi.entities.enums.LayoutTemplate;
import com.example.trendyolgobaseapi.entities.enums.ScrollDirection;

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
@Table(name = "list_ui")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListUI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private LayoutTemplate layoutTemplate;

    @Enumerated(EnumType.STRING)
    private ScrollDirection scrollDirection;

    @Enumerated(EnumType.STRING)
    private GroupOrientation groupOrientation;

    
    @Column(name = "buttontypes")
    private List<String> buttontypes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_info_id")
    private DataInfo dataInfo;

    // getters & setters
}
