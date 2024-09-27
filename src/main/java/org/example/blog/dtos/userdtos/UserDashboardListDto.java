package org.example.blog.dtos.userdtos;

import lombok.Getter;
import lombok.Setter;
import org.example.blog.models.Role;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDashboardListDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean emailConfirmed;
    private String ConfirmationToken;
    private List<Role> roles=new ArrayList<>();

}
