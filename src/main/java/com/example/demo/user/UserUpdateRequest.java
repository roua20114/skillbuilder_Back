package com.example.demo.user;

public record UserUpdateRequest(
        String name,
        String email
) {
}
