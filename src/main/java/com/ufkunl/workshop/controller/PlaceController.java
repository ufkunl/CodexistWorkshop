package com.ufkunl.workshop.controller;

import com.ufkunl.workshop.service.PlaceService;
import com.ufkunl.workshop.util.GenericServiceResult;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(@Qualifier("placeServiceImpl") PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/nearby-search")
    public GenericServiceResult getCategoryList(@RequestParam String longitude, @RequestParam String latitude, @RequestParam String radius) throws ParseException {
        return placeService.getPlaces(longitude,latitude,radius);
    }

}
