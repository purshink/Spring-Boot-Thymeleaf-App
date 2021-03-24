package com.example.hobbie.service.impl;

import com.example.hobbie.model.entities.AppClient;
import com.example.hobbie.model.entities.Test;
import com.example.hobbie.model.repostiory.TestRepository;
import com.example.hobbie.model.service.TestServiceModel;
import com.example.hobbie.service.LocationService;
import com.example.hobbie.service.TestService;
import com.example.hobbie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final LocationService locationService;

    @Autowired
    public TestServiceImpl(TestRepository testRepository, ModelMapper modelMapper, UserService userService, LocationService locationService) {
        this.testRepository = testRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.locationService = locationService;
    }

    @Override
    public void saveTest(TestServiceModel testServiceModel) {
        Test test = this.modelMapper.map(testServiceModel, Test.class);
        AppClient currentUserAppClient = this.userService.findCurrentUserAppClient();
        test.setAppClient(currentUserAppClient);
        test.setLocation((testServiceModel.getLocation()));
        if(currentUserAppClient.getTestResults() != null){
            test.setId(currentUserAppClient.getTestResults().getId());
        }
        currentUserAppClient.setTestResults(test);
        this.userService.saveUpdatedUserClient(currentUserAppClient);
        this.testRepository.save(test);

    }
}
