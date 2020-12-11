package com.javainuse;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {SpringFoxConfig.USER})
@RestController
@RequestMapping(path = "/users")
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
