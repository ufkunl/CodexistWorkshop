package com.ufkunl.workshop.service.impl;

import com.ufkunl.workshop.dto.PlaceDTO;
import com.ufkunl.workshop.entity.Place;
import com.ufkunl.workshop.mapper.BaseMapper;
import com.ufkunl.workshop.mapper.PlaceMapper;
import com.ufkunl.workshop.repository.PlaceRepository;
import com.ufkunl.workshop.service.PlaceService;
import com.ufkunl.workshop.util.GenericServiceResult;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    public PlaceServiceImpl(PlaceMapper placeMapper, PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
    }

    @Override
    public GenericServiceResult getPlaces(String longitude, String latitude, String radius) throws ParseException {

        final String uri = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyB2vmXQ-MvBH2F198x2SvujszQTujJrve4&location=41.235270, 28.494483&radius=100&type=restaurant";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        List<PlaceDTO> placeDTOS = new ArrayList<>();

        Object obj = new JSONParser().parse(result);
        JSONObject jo = (JSONObject) obj;
        if(jo.get("status").equals("OK")){
            List<Object> placeList = (List<Object>) jo.get("results");
            for (Object object : placeList) {
                PlaceDTO placeDTO = new PlaceDTO();
                placeDTO.setPlaceId(((JSONObject) object).get("place_id").toString());
                placeDTO.setName(((JSONObject) object).get("name").toString());
                Object opening_hours = ((JSONObject) object).get("opening_hours");
                if(opening_hours != null){
                    placeDTO.setIsOpen((Boolean) ((JSONObject) opening_hours).get("open_now"));
                }
                Object geometry = ((JSONObject) object).get("geometry");
                Object location = ((JSONObject) geometry).get("location");
                placeDTO.setLat(((JSONObject) location).get("lat").toString());
                placeDTO.setLng(((JSONObject) location).get("lng").toString());
                placeDTOS.add(placeDTO);
            }
        }

        List<Place> PlaceList = placeMapper.toEntityList(placeDTOS);

        return new GenericServiceResult(true,"İşlem Başarılı",placeDTOS);
    }
}
