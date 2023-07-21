package com.example.demo.user;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> selectAllUsers();
    Optional<User> selectUserById(Long Id);
    void insertUser(User user);
    boolean existUserWithEmail(String email);
    boolean existsUserById(Long Id);
    void deleteUserById(Long Id);
    void updateUser(User update);
    Optional<User> selectUserByEmail(String email);
    void updateUserProfileImageId(String profileImageId, Long Id);

}
