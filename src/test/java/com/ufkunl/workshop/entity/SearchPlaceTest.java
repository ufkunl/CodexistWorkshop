package com.ufkunl.workshop.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class SearchPlaceTest {

    private SearchPlace searchPlace;

    @BeforeEach
    void setUp() {
        searchPlace = new SearchPlace();
        searchPlace.setByLat("111");
        searchPlace.setByLng("111");
        searchPlace.setByRadius("100");
        searchPlace.setSearchDate(LocalDateTime.now());
    }

    @Test
    void testEquals() {
        SearchPlace instanceToCompare = createSearchPlace();
        SearchPlace nullRequest = null;
        Object nullObject = null;
        assertTrue(instanceToCompare.equals(searchPlace) && searchPlace.equals(instanceToCompare));
        assertNotEquals(searchPlace, nullRequest);
        assertNotEquals(searchPlace, nullObject);
    }

    @Test
    void testHashCode() {
        assertNotNull(searchPlace.hashCode());
    }

    @Test
    void testToString() {
        assertNotNull(searchPlace.toString());
    }

    private SearchPlace createSearchPlace(){
        SearchPlace searchPlace = new SearchPlace();
        searchPlace.setByLat("111");
        searchPlace.setByLng("111");
        searchPlace.setByRadius("100");
        searchPlace.setSearchDate(LocalDateTime.now());
        return searchPlace;
    }
}