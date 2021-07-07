package com.ufkunl.workshop.dto;

import lombok.Data;

@Data
public class PlaceDTO extends BaseDTO {
    private String name;
    private String lat;
    private String lng;
    private Boolean nowOpen;
}
