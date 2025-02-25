package com.school.onlineschool.domain.dto.response;

import lombok.Builder;

@Builder
public record StudentDetailsResponseDto(
        String courseName
) {

}
