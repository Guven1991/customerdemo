package com.example.customerdemo.serviceImpl;


import com.example.customerdemo.entity.AppUser;
import com.example.customerdemo.entity.AppUserRole;
import com.example.customerdemo.exception.CustomException;
import com.example.customerdemo.repository.UserRepository;
import com.example.customerdemo.security.JwtTokenProvider;
import com.example.customerdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public AppUser signin(AppUser appUser) {
        try {
            AppUser appUserDb = userRepository.findByUsername(appUser.getUsername());

            appUser.setEmail(appUserDb.getEmail());
            appUser.setId(appUserDb.getId());
            appUser.setAppUserRoles(appUserDb.getAppUserRoles());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
            appUser.setToken(jwtTokenProvider.createToken(appUser.getUsername(), userRepository.findByUsername(appUser.getUsername()).getAppUserRoles()));
            return appUser;
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @Override
    public String signup(AppUser appUser) {
        if (!userRepository.existsByUsername(appUser.getUsername())) {
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            userRepository.save(appUser);
            return jwtTokenProvider.createToken(appUser.getUsername(), appUser.getAppUserRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

//    @Override
//    @Transactional
//    public void makeAdmin(String username) {
//        userRepository.updateUserRole(username, AppUserRole.ADMIN);
//    }

//    @Override
//    public void delete(String username) {
//        userRepository.deleteByUsername(username);
//    }
//    @Override
//    public AppUser search(String username) {
//        AppUser appUser = userRepository.findByUsername(username);
//        if (appUser == null) {
//            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
//        }
//        return appUser;
//    }
//    @Override
//    public AppUser whoami(HttpServletRequest req) {
//        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
//    }
//    @Override
//    public String refresh(String username) {
//        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getAppUserRoles());
//    }

}
