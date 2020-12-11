package com.javainuse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@Tag(name = "User management", description = "Endpoint to manage users")
public class UserRestController {
    private UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Operation(summary = "Get all users")
    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(){
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setUserName("Karl");
        list.add(user);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
