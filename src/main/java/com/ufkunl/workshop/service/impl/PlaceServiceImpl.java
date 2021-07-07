package com.ufkunl.workshop.service.impl;

import com.ufkunl.workshop.dto.PlaceDTO;
import com.ufkunl.workshop.entity.Place;
import com.ufkunl.workshop.entity.SearchPlace;
import com.ufkunl.workshop.mapper.PlaceMapper;
import com.ufkunl.workshop.repository.PlaceRepository;
import com.ufkunl.workshop.repository.SearchPlaceRepository;
import com.ufkunl.workshop.service.PlaceService;
import com.ufkunl.workshop.util.Consts;
import com.ufkunl.workshop.util.GenericServiceResult;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @developer -- ufukunal
 */

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    private final SearchPlaceRepository searchPlaceRepository;

    @Value("${google.api.key}")
    public String apiKey;

    /**
     * Setter injections
     *
     * @param placeMapper
     * @param searchPlaceRepository
     * @param placeRepository
     */
    @Autowired
    public PlaceServiceImpl(PlaceMapper placeMapper,
                            PlaceRepository placeRepository,
                            SearchPlaceRepository searchPlaceRepository) {
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
        this.searchPlaceRepository = searchPlaceRepository;
    }

    /**
     * get places
     * @param
     * @return
     */
    @Override
    public GenericServiceResult getPlaces(String longitude, String latitude, String radius) throws ParseException {
        SearchPlace searchPlace = getPlacesFromDB(latitude,longitude,radius);
        if(searchPlace == null){
            searchPlace = getPlacesFromGoogleApi(latitude,longitude,radius);
        }
        return new GenericServiceResult(true,"İşlem Başarılı",placeMapper.toDTOList(searchPlace.getPlaceList()));
    }

    public SearchPlace getPlacesFromDB(String latitude , String longitude, String radius){
        return searchPlaceRepository.findByByLatAndByLngAndByRadius(latitude,longitude,radius);
    }

    public SearchPlace getPlacesFromGoogleApi(String latitude , String longitude, String radius) throws ParseException {
        final String uri = Consts.GOOGLE_API_PLACE_URL + "key=" + apiKey + "&location=" + latitude + "," + longitude + "&radius=" + radius;
        RestTemplate restTemplate = new RestTemplate();
        String jsonFromGoogleApi = restTemplate.getForObject(uri, String.class);
        List<PlaceDTO> placeDTOList = jsonConverter(jsonFromGoogleApi);
        List<Place> placeList = placeMapper.toEntityList(placeDTOList);
        return createSearchPlace(latitude,longitude,radius,placeList);
    }

    public SearchPlace createSearchPlace(String latitude , String longitude, String radius,List<Place> placeList){
        SearchPlace searchPlace = new SearchPlace();
        searchPlace.setByLat(latitude);
        searchPlace.setByLng(longitude);
        searchPlace.setByRadius(radius);
        searchPlace.setPlaceList(placeList);
        searchPlace = searchPlaceRepository.save(searchPlace);
        return searchPlace;
    }

    public List<PlaceDTO> jsonConverter(String placeJson) throws ParseException {
        List<PlaceDTO> placeDTOList = new ArrayList<>();
        Object obj = new JSONParser().parse(placeJson);
        JSONObject jo = (JSONObject) obj;
        if(jo.get("status").equals("OK")){
            List<Object> placeList = (List<Object>) jo.get("results");
            for (Object object : placeList) {
                PlaceDTO placeDTO = new PlaceDTO();
                placeDTO.setPlaceId(((JSONObject) object).get("place_id").toString());
                placeDTO.setName(((JSONObject) object).get("name").toString());
                Object opening_hours = ((JSONObject) object).get("opening_hours");
                if(opening_hours != null){
                    placeDTO.setNowOpen((Boolean) ((JSONObject) opening_hours).get("open_now"));
                }
                Object geometry = ((JSONObject) object).get("geometry");
                Object location = ((JSONObject) geometry).get("location");
                placeDTO.setLat(((JSONObject) location).get("lat").toString());
                placeDTO.setLng(((JSONObject) location).get("lng").toString());
                placeDTOList.add(placeDTO);
            }
        }
        return placeDTOList;
    }
}
