package com.cab.booking.cab.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    // Additional methods if needed

    List<Location> findByTripId(String tripId);


}
