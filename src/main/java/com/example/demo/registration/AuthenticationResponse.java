package com.example.demo.registration;

import com.example.demo.user.UserDTO;

public record AuthenticationResponse (
        String token,
       UserDTO userDTO

){

}
