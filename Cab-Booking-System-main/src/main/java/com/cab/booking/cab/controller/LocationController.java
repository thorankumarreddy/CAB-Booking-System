package com.cab.booking.cab.controller;

import com.cab.booking.cab.dto.User;
import com.cab.booking.cab.entity.Location;
import com.cab.booking.cab.entity.LocationRepository;
import com.cab.booking.cab.entity.LocationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.cab.booking.cab.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/save-location")
    public void saveLocation(@RequestBody LocationRequest locationRequest) {
        double latitude = locationRequest.getLatitude();
        double longitude = locationRequest.getLongitude();
        String tripId = locationRequest.getTripId();

        // Check if a location with the tripId already exists
        List<Location> existingLocations = locationRepository.findByTripId(tripId);
        if (!existingLocations.isEmpty()) {
            // Update the first existing location
            Location existingLocation = existingLocations.get(0);
            existingLocation.setLatitude(latitude);
            existingLocation.setLongitude(longitude);
            locationRepository.save(existingLocation);
            System.out.println("Overriding location data");
        } else {
            // Create a new Location entity
            Location newLocation = new Location(latitude, longitude, tripId);
            locationRepository.save(newLocation);
            System.out.println("Creating new location data");
        }
    }

    public class TripDetails {
        private List<Location> location;
        private User ride;

        public TripDetails(List<Location> location, User ride) {
            this.location = location;
            this.ride = ride;
        }

        public List<Location> getLocation() {
            return location;
        }

        public void setLocation(List<Location> location) {
            this.location = location;
        }

        public User getRide() {
            return ride;
        }

        public void setRide(User ride) {
            this.ride = ride;
        }
    }


    @GetMapping(value = "/trip-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public TripDetails getTripStatus(@RequestParam("tripId") String tripId) {
        // Retrieve the location history based on the tripId
        List<Location> driverLocation = locationRepository.findByTripId(tripId);
        User ride = userService.getCabBookingDetails(tripId);
        return new TripDetails(driverLocation, ride);
    }


}
