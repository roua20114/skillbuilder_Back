package com.example.demo.user;

import com.example.demo.registration.token.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@CrossOrigin("*")

public class UserController {

    private final UserService userService;
    private final JWTUtils jwtUtils;

    @GetMapping
    public List<UserDTO> getUsers(){

        return userService.getAllUsers();
    }
    @GetMapping("{userId}")
    public UserDTO getUser(
            @PathVariable("userId") Long Id) {
        return userService.getUser(Id);
    }
    @PostMapping
    public ResponseEntity<?> registerCustomer(
            @RequestBody UserRegistrationRequest request) {
        userService.addUser(request);
        String jwtToken = jwtUtils.issueToken(request.email(), "ROLE_USER");
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();
    }
    @DeleteMapping("{userId}")
    public void deleteCustomer(
            @PathVariable("customerId") Long Id) {
       userService.deleteUserById(Id);
    }

    @PutMapping("{Id}")
    public void updateCustomer(
            @PathVariable("Id") Long Id,
            @RequestBody UserUpdateRequest updateRequest) {
       userService.updateCustomer(Id, updateRequest);
    }

    @PostMapping(
            value = "{userId}/profile-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public void uploadCustomerProfileImage(
            @PathVariable("userId") Long Id,
            @RequestParam("file") MultipartFile file) {
     userService.uploadCustomerProfileImage(Id, file);
    }



}
