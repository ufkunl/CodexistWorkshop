package com.ufkunl.workshop.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Place extends BaseEntity{

    private String name;
    private String lat;
    private String lng;
    private boolean isOpen;

}
