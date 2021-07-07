package com.ufkunl.workshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @developer -- ufukunal
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "place")
public class Place extends BaseEntity{

    @Column(unique = true)
    private String placeId;

    @Column(nullable = false)
    private String name;

    private String lat;

    private String lng;

    private Boolean nowOpen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="search_place_id")
    private SearchPlace searchPlace;

}
