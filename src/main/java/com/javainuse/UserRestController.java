package com.javainuse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@Tag(name = "User management", description = "Endpoint to manage users")
public class UserRestController {
    private UserRepository userRepository;

    /**
     * Instantiates a new User RestController.
     *
     * @param userRepository            the user repository
     */
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Operation(summary = "Get all users")
    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
}