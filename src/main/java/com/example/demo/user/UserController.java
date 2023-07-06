package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping
    public List<User> getUsers(){

        return userService.getUsers();
    }
    @DeleteMapping ("/delete/{id}")


    @PutMapping("update")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);



    }
}
