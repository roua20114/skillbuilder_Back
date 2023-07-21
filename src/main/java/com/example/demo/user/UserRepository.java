package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;



@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsUserByEmail(String email);
    boolean existsUserById(Long id);
    Optional<User> findCustomerByEmail(String email);
    @Modifying(clearAutomatically = true)
    @Query("UPDATE User c SET c.profileImageId = ?1 WHERE c.id = ?2")
    int updateProfileImageId(String profileImageId, Long Id);


}
