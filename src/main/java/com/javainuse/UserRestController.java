package com.javainuse;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Api(tags = {SpringFoxConfig.USER})
@RestController
@RequestMapping(path = "/users")
public class UserRestController {
    private UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(summary = "Get all users")
    @GetMapping("/")
    public ResponseEntity getUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get all users")
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        ResponseEntity response;
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent())
            response = new ResponseEntity<>(user.get(), HttpStatus.OK);
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No User found");

        return response;
    }

    @Operation(summary = "Add a new user")
    @PostMapping("/")
    public ResponseEntity addUser(@RequestBody User newUser) {
        return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.OK);
    }


    @Operation(summary = "Replace a user with a new user")
    @PostMapping("/{id}")
    public ResponseEntity replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        User u;
        if(user.isPresent()){
            User temp = user.get();
            temp.setUserName(newUser.getUserName());
            temp.setBoards(newUser.getBoards());
            temp.setCoordinator(newUser.getCoordinator());
            temp.setEmail(newUser.getEmail());
            temp.setPassword(newUser.getPassword());
            temp.setSupervisor(newUser.getSupervisor());
            u = userRepository.save(temp);
        }else{
            newUser.setId(id);
            u = userRepository.save(newUser);
        }
        return new ResponseEntity<>(u, HttpStatus.OK);
    }


    @Operation(summary = "Delete a user")
    @DeleteMapping("/")
    public ResponseEntity addUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
