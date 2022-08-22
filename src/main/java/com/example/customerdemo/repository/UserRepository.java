package com.example.customerdemo.repository;


import com.example.customerdemo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


public interface UserRepository extends JpaRepository<AppUser, Integer> {

  boolean existsByUsername(String username);

  AppUser findByUsername(String username);

//  @Modifying
//  @Query("update AppUser set appUserRoles = :role where username = :username")
//  void updateUserRole(@Param("username") String username, @Param("role") AppUserRole role);

  @Transactional
  void deleteByUsername(String username);

}
