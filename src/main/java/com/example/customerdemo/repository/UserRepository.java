package com.example.customerdemo.repository;


import com.example.customerdemo.entity.AppUser;
import com.example.customerdemo.entity.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<AppUser, Integer> {

  boolean existsByUsername(String username);

  AppUser findByUsername(String username);

  @Modifying
  @Query("update AppUser set appUserRoles = :role where username = :username")
  void updateUserRole(@Param("username") String username, @Param("role") AppUserRole role);
  void deleteByUsername(String username);

}
