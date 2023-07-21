package com.example.demo.user;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service

@NoArgsConstructor(force = true)
public class UserUserDetailsService implements UserDetailsService {

   private final IUserService iUserService;

    public UserUserDetailsService(  @Qualifier IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            return iUserService.selectUserByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException(
                            "Username " + username + " not found"));
    }
}
