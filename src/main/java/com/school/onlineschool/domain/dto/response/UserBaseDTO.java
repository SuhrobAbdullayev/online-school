package com.school.onlineschool.domain.dto.response;


import com.school.onlineschool.domain.entiy.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class UserBaseDTO {
    @NotNull
    Long id;
    @NotNull
    String username;
    @NotNull
    String firstName;
    @NotNull
    String lastName;
    @NotNull
    Set<Role> roles;
}
