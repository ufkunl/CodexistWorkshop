package com.ufkunl.workshop.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @developer -- ufukunal
 */

@EqualsAndHashCode(of = "id")
@Data
public abstract class BaseDTO implements Serializable {

    private Long id;

}
