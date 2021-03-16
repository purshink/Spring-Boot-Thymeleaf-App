package com.example.hobbie.init;



import com.example.hobbie.service.CategoryService;
import com.example.hobbie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public DBInit(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.userService.seedUsersAndUserRoles();

        this.categoryService.initCategories();
    }

}
