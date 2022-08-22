package com.example.customerdemo.controller;


import com.example.customerdemo.dto.UserDataDTO;
import com.example.customerdemo.dto.UserResponseDTO;
import com.example.customerdemo.entity.AppUser;
import com.example.customerdemo.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    DozerBeanMapper modelMapper = new DozerBeanMapper();

    @PostMapping("/signin")
    public AppUser login(@RequestBody AppUser appUser) {
        return userService.signin(appUser);
    }


    @PostMapping("/signup")
    public String signup(@RequestBody UserDataDTO user) {
        return userService.signup(modelMapper.map(user, AppUser.class));
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO search(@PathVariable String username) {
        return modelMapper.map(userService.search(username), UserResponseDTO.class);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public UserResponseDTO whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }

}
