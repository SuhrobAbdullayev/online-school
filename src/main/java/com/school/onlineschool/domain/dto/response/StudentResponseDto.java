package com.school.onlineschool.domain.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record StudentResponseDto(
        Long id,
        String name,
        String username,
        List<UserDetailsResponseDto> studentDetails
) {
}
