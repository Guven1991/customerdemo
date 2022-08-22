package com.example.customerdemo.service;


import com.example.customerdemo.entity.AppUser;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    public AppUser signin(AppUser appUser);
    public String signup(AppUser appUser);

//    @Transactional
//    void makeAdmin(String username);

    public void delete(String username);
    public AppUser search(String username);
    public AppUser whoami(HttpServletRequest req);
    public String refresh(String username);
}
