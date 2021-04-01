package com.example.hobbie.service;

import com.example.hobbie.model.entities.Test;
import com.example.hobbie.model.service.TestServiceModel;

public interface TestService {
    Test saveTest(TestServiceModel map);
}
