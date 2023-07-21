package com.example.demo.user;

import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.RequestValidationException;
import com.example.demo.exception.ResourceNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor(force = true)

public class UserService {

    private final IUserService iUserService;
    private final UserDTOMapper userDTOMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(@Qualifier IUserService iUserService, UserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder) {
        this.iUserService = iUserService;
        this.userDTOMapper = userDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public List<UserDTO> getAllUsers() {

        return iUserService.selectAllUsers() .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }


    public UserDTO getUser(Long id) {
        return iUserService.selectUserById(id)
                .map(userDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "customer with id [%s] not found".formatted(id)
                ));
    }
    public void addUser(UserRegistrationRequest userRegistrationRequest) {
        // check if email exists
        String email = userRegistrationRequest.email();
        if (iUserService.existUserWithEmail(email)) {
            throw new DuplicateResourceException(
                    "email already taken"
            );
        }

        // add
        User user = new User(
                userRegistrationRequest.name(),
                userRegistrationRequest.email(),
                passwordEncoder.encode(userRegistrationRequest.password()));

        iUserService.insertUser(user);
    }


    public void deleteUserById(Long id) {
        checkIfUserExistsOrThrow(id);
       iUserService.deleteUserById(id);
    }

    private void checkIfUserExistsOrThrow(Long Id) {
        if (!iUserService.existsUserById(Id)) {
            throw new ResourceNotFoundException(
                    "customer with id [%s] not found".formatted(Id)
            );
        }
    }



    public void updateCustomer(Long Id,
                               UserUpdateRequest updateRequest) {
        // TODO: for JPA use .getReferenceById(customerId) as it does does not bring object into memory and instead a reference
            User user =iUserService.selectUserById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "customer with id [%s] not found".formatted(Id)
                ));

        boolean changes = false;

        if (updateRequest.name() != null && !updateRequest.name().equals(user.getName())) {
            user.setName(updateRequest.name());
            changes = true;
        }


        if (updateRequest.email() != null && !updateRequest.email().equals(user.getEmail())) {
            if (iUserService.existUserWithEmail(updateRequest.email())) {
                throw new DuplicateResourceException(
                        "email already taken"
                );
            }
           user.setEmail(updateRequest.email());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("no data changes found");
        }

        iUserService.updateUser(user);
    }



    public void uploadCustomerProfileImage(Long Id,
                                           MultipartFile file) {
        checkIfUserExistsOrThrow(Id);
        String profileImageId = UUID.randomUUID().toString();

        iUserService.updateUserProfileImageId(profileImageId,Id);
    }







}
