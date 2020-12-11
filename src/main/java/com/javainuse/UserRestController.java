package com.javainuse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
//@Tag(name = "User management", description = "Endpoint to manage users")
public class UserRestController {
    private UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(summary = "Get all users")
    @GetMapping(path = "/")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get all users")
    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        ResponseEntity<User> response;
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
