package com.example.hobbie.init;



import com.example.hobbie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public DBInit(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.userService.seedUsers();
    }

}
