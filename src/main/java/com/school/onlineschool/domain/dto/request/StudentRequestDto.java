package com.school.onlineschool.domain.dto.request;

import lombok.Builder;

@Builder
public record StudentRequestDto(
        String name,
        String phoneNumber

) {
}
