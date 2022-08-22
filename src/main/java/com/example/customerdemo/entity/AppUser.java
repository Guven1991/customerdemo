package com.example.customerdemo.entity;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
  @Column(unique = true, nullable = false)
  private String username;

  @Column(unique = true, nullable = false)
  private String email;

  @Size(min = 8, message = "Minimum password length: 8 characters")
  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  List<AppUserRole> appUserRoles;

  @Transient
  private String token;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public AppUser() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AppUser appUser = (AppUser) o;
    return Objects.equals(id, appUser.id) && Objects.equals(username, appUser.username) && Objects.equals(email, appUser.email) && Objects.equals(password, appUser.password) && Objects.equals(appUserRoles, appUser.appUserRoles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, password, appUserRoles);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<AppUserRole> getAppUserRoles() {
    return appUserRoles;
  }

  public void setAppUserRoles(List<AppUserRole> appUserRoles) {
    this.appUserRoles = appUserRoles;
  }
}
