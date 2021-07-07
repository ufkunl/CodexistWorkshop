package com.ufkunl.workshop.service;

import com.ufkunl.workshop.dto.PlaceDTO;
import com.ufkunl.workshop.entity.Place;
import com.ufkunl.workshop.entity.SearchPlace;
import com.ufkunl.workshop.mapper.PlaceMapper;
import com.ufkunl.workshop.repository.PlaceRepository;
import com.ufkunl.workshop.repository.SearchPlaceRepository;
import com.ufkunl.workshop.service.impl.PlaceServiceImpl;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class PlaceServiceTest {

    @InjectMocks
    private PlaceServiceImpl placeService;

    @Mock
    private SearchPlaceRepository searchPlaceRepository;

    @Mock
    private PlaceMapper placeMapper;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_get_place_from_db() throws ParseException {
        Mockito.when(searchPlaceRepository.findByByLatAndByLngAndByRadius("1","1","10")).thenReturn(createSearchPlace());
        Mockito.when(placeMapper.toDTOList(createSearchPlace().getPlaceList())).thenReturn(createPlaceDTOList());
        Assert.assertEquals(createPlaceDTOList(), placeService.getPlaces("1","1","10").getData());
    }

    private SearchPlace createSearchPlace(){
        SearchPlace searchPlace = new SearchPlace();
        searchPlace.setByLat("111");
        searchPlace.setByLng("111");
        searchPlace.setByRadius("100");
        searchPlace.setSearchDate(LocalDateTime.now());
        searchPlace.setPlaceList(createPlaceList());
        return searchPlace;
    }

    private Place createPlace(){
        Place place = new Place();
        place.setLat("111");
        place.setLng("111");
        place.setName("test");
        place.setNowOpen(true);
        place.setPlaceId("111");
        return place;
    }

    private PlaceDTO createPlaceDTO(){
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setLat("111");
        placeDTO.setLng("111");
        placeDTO.setName("test");
        placeDTO.setNowOpen(true);
        placeDTO.setPlaceId("111");
        return placeDTO;
    }

    private List<Place> createPlaceList(){
        List<Place> placeList = new ArrayList<>();
        placeList.add(createPlace());
        placeList.add(createPlace());
        return placeList;
    }

    private List<PlaceDTO> createPlaceDTOList(){
        List<PlaceDTO> placeDTOList = new ArrayList<>();
        placeDTOList.add(createPlaceDTO());
        placeDTOList.add(createPlaceDTO());
        return placeDTOList;
    }


}