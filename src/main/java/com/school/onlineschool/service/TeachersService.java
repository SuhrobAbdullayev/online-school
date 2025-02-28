package com.school.onlineschool.service;

import com.school.onlineschool.domain.dto.request.TeacherRequestDto;
import com.school.onlineschool.domain.dto.response.TeacherResponseDto;

public interface TeachersService {
    Long createTeacher(TeacherRequestDto teacherRequestDto);

    TeacherResponseDto getTeacher(Long id);

}
