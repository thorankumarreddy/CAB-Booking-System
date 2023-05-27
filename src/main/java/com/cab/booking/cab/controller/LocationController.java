package com.cab.booking.cab.controller;

import com.cab.booking.cab.entity.Location;
import com.cab.booking.cab.entity.LocationRepository;
import com.cab.booking.cab.entity.LocationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import  com.cab.booking.cab.service.UserService;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @PostMapping("/save-location")
    public void saveLocation(@RequestBody LocationRequest locationRequest) {
        double latitude = locationRequest.getLatitude();
        double longitude = locationRequest.getLongitude();
        String tripId= locationRequest.getTripId();

        // Create a new Location entity
        Location location = new Location(latitude, longitude, tripId);
        // Save the location in the database
        locationRepository.save(location);
//        UserService service;
//        service.statusUpdate(tripId,);

    }

    @GetMapping("/get-location-history")
    public List<Location> getLocationHistory(@RequestParam("tripId") String tripId) {
        // Retrieve the location history based on the tripId
        List<Location> locationHistory = locationRepository.findByTripId(tripId);
        return locationHistory;
    }



}
