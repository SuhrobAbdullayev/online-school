package com.school.onlineschool.service;

import com.school.onlineschool.domain.dto.request.StudentRequestDto;
import com.school.onlineschool.domain.dto.response.StudentResponseDto;
import org.springframework.util.MultiValueMap;

import java.util.List;


public interface StudentsService {

    Long createStudent(StudentRequestDto dto);

    StudentResponseDto getStudent(Long id);

    List<StudentResponseDto> getAll(MultiValueMap<String, Object> filter);

}
