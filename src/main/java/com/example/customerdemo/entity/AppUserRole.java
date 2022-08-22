package com.example.customerdemo.entity;

import org.springframework.security.core.GrantedAuthority;

public enum AppUserRole implements GrantedAuthority {
  ADMIN, USER;

  public String getAuthority() {

    return name();
  }

}
