package org.example.blog.repositories;

import org.example.blog.dtos.userdtos.UserDashboardListDto;
import org.example.blog.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
}
