package com.ime.api.controller;

import com.ime.api.model.User;
import com.ime.api.repository.UserRepository;
import com.ime.api.security.JwtService;
import com.ime.api.service.dto.LoginRequest;
import com.ime.api.service.dto.UserDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepo;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepo.findByEmail(request.getEmail());

        String token = jwtService.generateToken(user.getEmail());

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);


        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", userDTO);

        return response;
    }
    
}
