package com.school.onlineschool.domain.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record TeacherResponseDto(
        Long id,
        String name,
        String courseName,
        List<StudentNamesResponceDto> studentNamesResponceDtos
) {

}
