package com.example.demo.registration;

import com.example.demo.registration.token.JWTUtils;
import com.example.demo.user.User;
import com.example.demo.user.UserDTO;
import com.example.demo.user.UserDTOMapper;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor(force = true)
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtil;
    private final UserDTOMapper userDTOMapper;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 UserDTOMapper userDTOMapper,
                                 JWTUtils jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDTOMapper = userDTOMapper;
        this.jwtUtil = jwtUtil;
    }
    public AuthenticationResponse login(RegistrationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        User principal = (User) authentication.getPrincipal();
       UserDTO userDTO = userDTOMapper.apply(principal);
        String token = jwtUtil.issueToken(userDTO.username(), userDTO.roles());
        return new AuthenticationResponse(token, userDTO);
    }

}
