package com.example.hobbie.service.impl;

import com.example.hobbie.model.entities.Category;
import com.example.hobbie.model.entities.Location;
import com.example.hobbie.model.entities.enums.CategoryNameEnum;
import com.example.hobbie.model.entities.enums.LocationEnum;
import com.example.hobbie.model.repostiory.LocationRepository;
import com.example.hobbie.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public void initLocations() {
        if(locationRepository.count() == 0) {
            Arrays.stream(LocationEnum.values()).forEach(locationEnum -> {
                Location location = new Location(locationEnum);
                this.locationRepository.save(location);

            });
        }
    }

    @Override
    public Location getLocationByName(LocationEnum locationEnum) {
        Optional<Location> location = this.locationRepository.findByName(locationEnum);

        if(location.isPresent()) {
            return location.get();
        }
        else {
            throw new NullPointerException();
        }

    }
}