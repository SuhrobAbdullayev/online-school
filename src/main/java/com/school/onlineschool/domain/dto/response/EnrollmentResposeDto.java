package com.school.onlineschool.domain.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record EnrollmentResposeDto(
        Long id,
        String name,
        String phoneNumber,
        List<UserDetailsResponseDto> studentDetails
) {
}
