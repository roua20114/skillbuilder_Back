package com.example.demo.user;

import java.util.List;

public record UserDTO (
        Long id,
        String name,
        String email,

        List<String> roles,
        String username,
        String profileImageId
){
}
