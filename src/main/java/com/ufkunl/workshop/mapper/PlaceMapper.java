package com.ufkunl.workshop.mapper;

import com.ufkunl.workshop.dto.PlaceDTO;
import com.ufkunl.workshop.entity.Place;
import org.mapstruct.Mapper;

/**
 * @developer -- ufukunal
 */

@Mapper(componentModel = "spring")
public interface PlaceMapper extends BaseMapper<Place, PlaceDTO>{
}
