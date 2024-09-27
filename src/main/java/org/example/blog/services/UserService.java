package org.example.blog.services;

import org.example.blog.dtos.authdtos.RegisterDto;
import org.example.blog.dtos.userdtos.UserDashboardListDto;

import java.util.List;

public interface UserService {
    boolean register(RegisterDto registerDto);
    boolean confirmEmail(String email,String token);
    List<UserDashboardListDto> getDashboardUsers();

}
