package com.ufkunl.workshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @developer -- ufukunal
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "search_place")
public class SearchPlace extends BaseEntity {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime searchDate = LocalDateTime.now();

    private String byLat;

    private String byLng;

    private String byRadius;

    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "search_place_id")
    private List<Place> placeList;
}
