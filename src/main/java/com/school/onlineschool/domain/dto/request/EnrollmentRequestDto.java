package com.school.onlineschool.domain.dto.request;

import java.util.List;

public record EnrollmentRequestDto(
        Long studentId,
        List<EnrollmentDetailsRequestDto> courseIds
) {

}
