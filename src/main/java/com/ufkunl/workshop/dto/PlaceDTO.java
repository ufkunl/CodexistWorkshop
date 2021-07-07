package com.ufkunl.workshop.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @developer -- ufukunal
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class PlaceDTO extends BaseDTO {
    private String placeId;
    private String name;
    private String lat;
    private String lng;
    private Boolean nowOpen;
}
