package com.school.onlineschool.service;


import com.school.onlineschool.controller.VM.LoginVM;
import com.school.onlineschool.domain.dto.ResponseDTO;
import com.school.onlineschool.domain.dto.request.users.UserRequestDto;
import com.school.onlineschool.domain.dto.response.LoginResponseDTO;
import com.school.onlineschool.domain.dto.response.StudentResponseDto;
import com.school.onlineschool.domain.dto.response.users.UserResponseDto;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface UserService {

    ResponseDTO<LoginResponseDTO> login(LoginVM loginVM);

    ResponseDTO<UserResponseDto> createOrUpdateUser(UserRequestDto userRequestDto, Long userId);

    Boolean checkUsername(String username);

    Boolean checkPassword(String password);

    void logout(String username);

    StudentResponseDto getStudent(Long id);

    List<StudentResponseDto> getAll();


    List<StudentResponseDto> getStudentByName(MultiValueMap<String, Object> filter);
}
