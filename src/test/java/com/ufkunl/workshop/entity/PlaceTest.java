package com.ufkunl.workshop.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
class PlaceTest {

    private Place place;

    @BeforeEach
    void setUp() {
        place = new Place();
        place.setLat("111");
        place.setLng("111");
        place.setName("test");
        place.setNowOpen(true);
        place.setPlaceId("111");
    }

    @Test
    void testEquals() {
        Place instanceToCompare = createPlace();
        Place nullRequest = null;
        Object nullObject = null;
        assertTrue(instanceToCompare.equals(place) && place.equals(instanceToCompare));
        assertNotEquals(place, nullRequest);
        assertNotEquals(place, nullObject);
    }

    @Test
    void testHashCode() {
        assertNotNull(place.hashCode());
    }

    @Test
    void testToString() {
        assertNotNull(place.toString());
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
}