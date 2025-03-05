package com.school.onlineschool.domain.dto.response.users;

import com.school.onlineschool.domain.entiy.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Set<Role> roles;
}
