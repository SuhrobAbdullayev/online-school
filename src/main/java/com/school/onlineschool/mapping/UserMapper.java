package com.school.onlineschool.mapping;

import com.school.onlineschool.domain.dto.request.users.UserRequestDto;
import com.school.onlineschool.domain.dto.response.users.UserResponseDto;
import com.school.onlineschool.domain.entiy.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapping<User, UserRequestDto, UserResponseDto> {

}
