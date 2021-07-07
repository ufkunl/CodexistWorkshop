package com.ufkunl.workshop.repository;

import com.ufkunl.workshop.entity.SearchPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @developer -- ufukunal
 */

@Repository
public interface SearchPlaceRepository extends JpaRepository<SearchPlace,Long> {

    SearchPlace findByByLatAndByLngAndByRadius(String lat, String lng, String radius);

}
