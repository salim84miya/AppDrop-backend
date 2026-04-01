package com.appdeploy.fastdeploy.security.service;

import com.appdeploy.fastdeploy.security.dto.UserDto;
import com.appdeploy.fastdeploy.security.entity.User;
import com.appdeploy.fastdeploy.security.repository.UserRepository;
import com.appdeploy.fastdeploy.security.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public String loginUser(UserDto userDto ){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword()
                ));

        User user = (User) authentication.getPrincipal();

        return authUtil.createAccessToken(user);
    }


    public User signup(UserDto userDto){


        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);

    }

    public User fetchUserByUsername(String username){

        return  userRepository.findByUsername(username);
    }

}
