package com.example.ServiceDeskV1.repository;

import com.example.ServiceDeskV1.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppUserRepository extends JpaRepository<AppUser,Long> {
}
