package com.example.hobbie.service;

import com.example.hobbie.model.entities.Location;
import com.example.hobbie.model.entities.enums.LocationEnum;

public interface LocationService {
    void initLocations();

    Location getLocationByName(LocationEnum locationEnum);
}
