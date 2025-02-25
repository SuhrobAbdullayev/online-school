package com.school.onlineschool.domain.dto.response;

import java.util.List;

public record StudentResponseDto(
        Long id,
        String name,
        String phoneNumber,
        List<StudentDetailsResponseDto> studentDetails
) {
}
