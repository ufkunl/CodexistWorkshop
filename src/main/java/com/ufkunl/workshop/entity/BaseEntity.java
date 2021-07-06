package com.ufkunl.workshop.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @developer -- ufukunal
 */

@Data
@EqualsAndHashCode(of = "placeId")
@MappedSuperclass
public class BaseEntity {

    @Id
    private String placeId;

}
